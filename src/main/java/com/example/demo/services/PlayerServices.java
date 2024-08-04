package com.example.demo.services;

import com.example.demo.models.Player;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public interface PlayerServices {
    Player getPlayerById(Long id);

    Player savePlayer(Player player);

    //LOGGIN
    Player getPlayerByUserNameAndPassword(String userName, String password);

    Player getPlayerByEmailAndPassword(String email, String password);

    Player getPlayerByUserNameOrEmailAndPassword(String identity, String password);

}
