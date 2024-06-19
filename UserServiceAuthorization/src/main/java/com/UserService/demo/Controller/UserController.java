package com.UserService.demo.Controller;

import com.UserService.demo.DTOs.UserDTO;
import com.UserService.demo.Model.User;
import com.UserService.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserDetails(@PathVariable Long id){
        System.out.println(id);
        User user=userService.getUserDetails(id);
        if(user == null) return null;

        return getUserDTO(user);
    }

    private UserDTO getUserDTO(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

}
