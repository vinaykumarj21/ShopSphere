package com.UserService.demo.Repository;

import com.UserService.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
        Optional<User> findByEmail(String email);
        Optional<User> findById(Long userId);
}
