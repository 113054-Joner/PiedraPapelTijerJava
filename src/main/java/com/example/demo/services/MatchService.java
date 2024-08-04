package com.example.demo.services;


import com.example.demo.dtos.match.MatchDTO;
import com.example.demo.dtos.play.PlayRequest;
import com.example.demo.models.Match;
import com.example.demo.models.Play;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {

    List<Match> getMatchesByPlayer(Long playerId);

    Match createMatch(MatchDTO matchDTO);

    Match getMatchById(Long id);

    Play play(Long matchId, PlayRequest play);
}
