package com.UserService.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    
    private String email;
    private String password;

    @ManyToMany
    private Set<Role> roleList=new HashSet<>();     //Role is Always Unique
}

//  User    Role
//   1        M
//   M        1