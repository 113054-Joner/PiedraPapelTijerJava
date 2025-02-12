package com.example.demo.controllers;


import com.example.demo.dtos.match.MatchDTO;
import com.example.demo.dtos.play.PlayRequest;
import com.example.demo.models.Match;
import com.example.demo.models.Play;
import com.example.demo.services.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;


    @PostMapping("")
    public ResponseEntity<Match> saveMatch(@RequestBody @Valid MatchDTO matchDTO) {
        Match matchSaved = matchService.createMatch(matchDTO);
        if(Objects.isNull(matchSaved)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has an error");
        } else {
            return ResponseEntity.ok(matchSaved);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @PostMapping("/{id}/plays/")
    public ResponseEntity<Play> saveMatch(@PathVariable Long id, @RequestBody @Valid PlayRequest playRequest) {
        Play playResult = matchService.play(id, playRequest);
        if(Objects.isNull(playResult)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has an error");
        } else {
            return ResponseEntity.ok(playResult);
        }
    }
}
