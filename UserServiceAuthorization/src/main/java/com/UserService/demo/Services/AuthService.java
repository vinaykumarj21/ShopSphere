package com.UserService.demo.Services;

import com.UserService.demo.Clients.KafkaProducerClient;
import com.UserService.demo.DTOs.SendMessageDTO;
import com.UserService.demo.DTOs.UserDTO;
import com.UserService.demo.Model.Session;
import com.UserService.demo.Model.SessionStatus;
import com.UserService.demo.Model.User;
import com.UserService.demo.Repository.SessionRepo;
import com.UserService.demo.Repository.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.kafka.common.network.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private SecretKey secret;

    @Autowired
    private KafkaProducerClient kafkaProducerClient;

    @Autowired
    private ObjectMapper objectMapper;
    //for Serialization-Deserialization

    public User signUp(String email, String password){
       Optional<User> userOptional= userRepo.findByEmail(email);
       if(userOptional.isEmpty()){//if the user is not there
           //we should create User
            User user=new User();
            user.setEmail(email);
           //user.setPassword(password);
           //now storing the Encrypted Password in the DB
            user.setPassword(bCryptPasswordEncoder.encode(password));
            User savedUser=userRepo.save(user);
            return savedUser;
       }


        UserDTO userDTO=new UserDTO();
        userDTO.setEmail(email);

        try {
            SendMessageDTO sendMessageDTO=new SendMessageDTO();

            sendMessageDTO.setTo(email);
            sendMessageDTO.setFrom("admin@scaler.com");
            sendMessageDTO.setSubject("Welcome : " +email);
            sendMessageDTO.setBody("Sign up Successful in Application ");
//put message in queue
            kafkaProducerClient.sendMessage("sendEmail", objectMapper.writeValueAsString(sendMessageDTO));
            } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userOptional.get();//return the already present user
    }

    public Pair<User,MultiValueMap<String,String>> login(String email,String password){
        Optional<User> userOptional=userRepo.findByEmail(email);
        if(userOptional.isEmpty()){//if the user is not present (Checked by emailID)
            return null;
        }

        User user=userOptional.get();
       /* if(!user.getPassword().equals(password)){//user is present but the password NOT matches
            return null;
        }*/

        //Matching the encrypted password and the password user has sent(Both encrypted)s
        if(!bCryptPasswordEncoder.matches(password,user.getPassword()))
            return null;

        //Token Generation
       /* String Message = "{\n" +
                  "   \"email\": \"anurag@scaler.com\",\n" +
                  "   \"roles\": [\n" +
                  "      \"instructor\",\n" +
                  "      \"buddy\"\n" +
                  "   ],\n" +
                  "   \"expirationDate\": \"2ndApril2024\"\n" +
              "}";*/

      /*  1. convert message to Byte Array
        byte[]Content=Message.getBytes(StandardCharsets.UTF_8);

        //Generation of token
         String token= Jwts.builder().content(Content).compact();

       // setting the cookie value to token
         MultiValueMap<String,String>headers=new LinkedMultiValueMap();
         headers.add(HttpHeaders.SET_COOKIE,token);
         String token= Jwts.builder().content(Content).signWith(secret).compact();

         return new Pair<User,MultiValueMap<String,String>>(user,headers);

        //Adding the Signature in JWT token
        //Bean named SecretKey is created in SpringSecurity Config file
        MacAlgorithm macAlgorithm=Jwts.SIG.HS256;
        SecretKey secret=macAlgorithm.key().build();
         String token= Jwts.builder().claims(jwtData).signWith(secret).compact();
        //signing token with the secret to avoid the Tampering with Token */


        Map<String,Object>jwtData=new HashMap<>();
        jwtData.put("email",user.getEmail());
        jwtData.put("roles",user.getRoleList());
        Long getTimeInMilis=System.currentTimeMillis();
        jwtData.put("expiryTime",new Date(getTimeInMilis+10000000));
        jwtData.put("createdAT",new Date(getTimeInMilis));

        String token= Jwts.builder().claims(jwtData).signWith(secret).compact();
        MultiValueMap<String,String>headers=new LinkedMultiValueMap();
        headers.add(HttpHeaders.SET_COOKIE,token);//setting the cookie value to token

        Session session=new Session();
        session.setUser(user);
        session.setToken(token);
        session.setSessionStatus(SessionStatus.Active);
        session.setExpiryAt(new Date(getTimeInMilis+10000));
        sessionRepo.save(session);

        return new Pair<User,MultiValueMap<String,String>>(user,headers);
        //returning user along with token
    }
//step 5 -> Client will be coming to Resource Server ( token + Response from user)
    public Boolean validate(String token,Long userId){
            Optional<Session>optionalSession=sessionRepo.findByTokenAndUser_Id(token,userId);

            if(optionalSession.isEmpty()){
                System.out.println("No user or Token found");
                return false;
            }

            Session session= optionalSession.get();//particular session returned
            String tokens=session.getToken();

           //parsing : processing / fetching the info.
        JwtParser parser=Jwts.parser().verifyWith(secret).build();//fetching the secret
        Claims claims=parser.parseSignedClaims(tokens).getPayload();//obtain the payload
        System.out.println(claims);

        Long nowInMillis=System.currentTimeMillis();
        Long tokenExpiry=(Long)claims.get("expiryTime");
        //in Map key for ExpiryTime: "expiryTime"

        if(nowInMillis>tokenExpiry){
            System.out.println("Token is Expired");
            System.out.println("Time:"+nowInMillis);
            System.out.println("expiryTime:"+ tokenExpiry);
            return false;
        }

        Optional<User>optionalUser=userRepo.findById(userId);
        if(optionalUser.isEmpty()){
            return false;
        }

        String email=optionalUser.get().getEmail();
        if(!email.equals(claims.get("email"))) { //key for storing email: "email"
            System.out.println(email);
            System.out.println(claims.get("email"));
            System.out.println("email Not Matching");
            return false;
        }

     return true;
    }

}
