package com.UserService.demo.Services;

import com.UserService.demo.Model.User;
import com.UserService.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUserDetails(Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()) return optionalUser.get();
        System.out.println("Control in UserService");
        return null;
    }

}
