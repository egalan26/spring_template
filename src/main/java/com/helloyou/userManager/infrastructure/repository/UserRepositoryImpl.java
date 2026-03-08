package com.helloyou.userManager.infrastructure.repository;

import com.helloyou.userManager.domain.interfaces.UserRepository;
import com.helloyou.userManager.domain.models.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserRepositoryImpl implements UserRepository {
    public UserRepositoryImpl() {
    }

    @Override
    public String newUser() {
        return "ok";
    }

    @Override
    public User findByEmail(String email) {

        User user = new User();
        user.setUsername("admin");
        user.setEmail("sadas@asdd.c");
        user.setRole("admin");
        return user;
    }
}
