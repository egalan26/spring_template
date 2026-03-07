package com.helloyou.vilia.infrastructure.controllers;

import com.helloyou.vilia.infrastructure.repository.GameSessionRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/game-sessions")
public class GameSessionController {

    GameSessionRepository gameSessionRepository;

    GameSessionController(GameSessionRepository gameSessionRepository) {
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