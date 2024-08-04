package com.example.demo.services.impl;


import com.example.demo.entities.GameEntity;
import com.example.demo.models.Game;
import com.example.demo.repositories.jpa.GameJpaRepository;
import com.example.demo.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameJpaRepository gameJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Game getGame(Long gameId) {
        GameEntity gameEntity = gameJpaRepository.getReferenceById(gameId);
        return modelMapper.map(gameEntity, Game.class);
    }
}
