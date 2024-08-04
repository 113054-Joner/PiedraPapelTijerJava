package com.example.demo.services;


import com.example.demo.models.Game;
import org.springframework.stereotype.Service;

@Service
public interface GameService {

    Game getGame(Long gameId);
}
