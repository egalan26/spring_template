package com.helloyou.vilia.infrastructure.controllers;

import com.helloyou.vilia.infrastructure.repository.GameSessionRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/game-sessions")
public class GameSessionController {

    GameSessionRepositoryImpl gameSessionRepository;

    GameSessionController(GameSessionRepositoryImpl gameSessionRepository) {
        this.gameSessionRepository = gameSessionRepository;
    }

    @GetMapping
    public ResponseEntity getAllSessions(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String system// D&D, Pathfinder...

    ) throws IOException {
        ArrayList list =gameSessionRepository.findAll(status, system);
        return ResponseEntity.ok(list);
    }

}