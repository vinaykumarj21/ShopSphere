package com.UserService.demo.Repository;

import com.UserService.demo.Model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepo extends JpaRepository<Session,Long> {
    Optional<Session>findByTokenAndUser_Id(String token, Long id);
}
