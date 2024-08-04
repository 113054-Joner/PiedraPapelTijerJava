package com.example.demo.services.impl;

import com.example.demo.entities.PlayerEntity;
import com.example.demo.models.Player;
import com.example.demo.repositories.PlayerJpaRepository;
import com.example.demo.services.PlayerServices;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerServices {

    @Autowired
    PlayerJpaRepository playerJpaRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Player getPlayerById(Long id) {
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);
        if(Objects.isNull(playerEntity.getUserName())) {
            throw new EntityNotFoundException();
        }
        Player player = modelMapper.map(playerEntity, Player.class);
        return player;
    }

    @Override
    public Player savePlayer(Player player) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameOrEmail(
                player.getUserName(), player.getEmail());
        if(playerEntityOptional.isEmpty()) {
            PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
            PlayerEntity playerEntitySaved = playerJpaRepository.save(playerEntity);
            return modelMapper.map(playerEntitySaved, Player.class);
        } else {
            //throw new EntityNotFoundException();
            return null;
        }
    }
}
