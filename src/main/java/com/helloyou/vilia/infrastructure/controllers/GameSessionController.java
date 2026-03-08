package com.helloyou.vilia.infrastructure.controllers;

import com.helloyou.vilia.infrastructure.repository.GameSessionRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/game-sessions")
@Tag(name = "Users", description = "Gestión de usuarios")  // agrupa en la UI
public class GameSessionController {

    GameSessionRepositoryImpl gameSessionRepository;

    GameSessionController(GameSessionRepositoryImpl gameSessionRepository) {
        this.gameSessionRepository = gameSessionRepository;
    }

    @Operation(summary = "Obtiene todos los usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    @GetMapping
    public ResponseEntity getAllSessions(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String system// D&D, Pathfinder...

    ) throws IOException {
        ArrayList list =gameSessionRepository.findAll(status, system);
        return ResponseEntity.ok(list);
    }

}