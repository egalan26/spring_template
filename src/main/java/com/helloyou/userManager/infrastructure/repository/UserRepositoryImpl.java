package com.helloyou.userManager.infrastructure.repository;

import com.helloyou.userManager.app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements UserRepository {
    public UserRepositoryImpl() {
    }

    @Override
    public String newUser() {
        return "ok";
    }
}
