package com.cipheredcalls.spring_sec.repos;

import com.cipheredcalls.spring_sec.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findOneByUsername(String username);
}
