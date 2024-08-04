package com.example.demo.controllers;

import com.example.demo.models.Player;
import com.example.demo.services.PlayerServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerServices playerService;
    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable Long id){
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PostMapping("")
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player player) {
        Player playerSaved = playerService.savePlayer(player);
        if(Objects.isNull(playerSaved)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username o email ya existen");
        } else {
            return ResponseEntity.ok(playerSaved);
        }
    }
}
