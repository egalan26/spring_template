package com.helloyou.vilia.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.helloyou.vilia.domain.dto.GameSessionResponse;
import com.helloyou.vilia.domain.models.GameSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
@RequiredArgsConstructor
public class GameSessionRepositoryImpl {
    private final ObjectMapper objectMapper; // Jackson ya es un bean en Spring Boot

    public GameSessionRepositoryImpl() {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public ArrayList<GameSession> findAll(String status, String system) throws IOException {
        ClassPathResource resource = new ClassPathResource("data/games/games.txt");
        String json = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return objectMapper.readValue(
                json,
                objectMapper.getTypeFactory().constructCollectionType(List.class, GameSessionResponse.class)
        );
    }
}
