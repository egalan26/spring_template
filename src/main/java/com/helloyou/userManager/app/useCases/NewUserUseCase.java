package com.helloyou.userManager.app.useCases;

import com.helloyou.userManager.app.repository.UserRepository;
import com.helloyou.userManager.infrastructure.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class NewUserUseCase {

    private final UserRepositoryImpl userRepository;

    public NewUserUseCase(
            UserRepositoryImpl userRepository
    ) {
        this.userRepository = userRepository;
    }

    public String execute() {

        return userRepository.newUser();

    }
}
