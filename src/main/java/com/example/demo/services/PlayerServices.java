package com.example.demo.services;

import com.example.demo.models.Player;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public interface PlayerServices {
    Player getPlayerById(Long id);

    Player savePlayer(Player player);

}
