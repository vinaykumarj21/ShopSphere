package com.UserService.demo.Security.Services;

import com.UserService.demo.Model.User;
import com.UserService.demo.Repository.UserRepo;
import com.UserService.demo.Security.Models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


//Override the UserDetailsService : we want to use the users from over Repo
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("user Not Found");
        }
        User user=optionalUser.get();
        return new CustomUserDetails(user);
    }
}
