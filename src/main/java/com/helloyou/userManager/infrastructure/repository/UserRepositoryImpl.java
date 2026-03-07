package com.helloyou.userManager.infrastructure.repository;

import com.helloyou.userManager.domain.interfaces.UserRepository;
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
}
