package com.UserService.demo.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

@Configuration
public class SpringSecurity {
    //SecurityFilterChain-> this is something which is getting Controlled & we need to override this
    //we want that SpringBoot should take this bean Rather than Creating on its own
    //overriding the properties
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();

        httpSecurity.authorizeHttpRequests(authorize->authorize.anyRequest().permitAll());
        return httpSecurity.build();
    }


    //creating bean for BCryptPasswordEncoder
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecretKey secret(){//we want a singleton object so we are creating a bean
        MacAlgorithm macAlgorithm= Jwts.SIG.HS256;
        SecretKey secret=macAlgorithm.key().build();
        return secret;
    }

}
