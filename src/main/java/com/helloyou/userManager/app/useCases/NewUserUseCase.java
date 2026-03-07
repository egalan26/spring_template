package com.helloyou.userManager.app.useCases;

import com.helloyou.userManager.domain.interfaces.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class NewUserUseCase {

    private final UserRepository userRepository;

    public NewUserUseCase(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public String execute() {

        return userRepository.newUser();

    }
}
