package com.helloyou.userManager.domain.interfaces;


import com.helloyou.userManager.domain.models.User;

public interface UserRepository {

    public String newUser();

    User findByEmail(String email);
}
