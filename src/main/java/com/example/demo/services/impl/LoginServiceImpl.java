package com.example.demo.services.impl;


import com.example.demo.dtos.login.Credential;
import com.example.demo.dtos.login.CredentialV2;
import com.example.demo.dtos.login.EmailIdentity;
import com.example.demo.dtos.login.UserNameIdentity;

import com.example.demo.models.Player;
import com.example.demo.services.LoginService;
import com.example.demo.services.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PlayerServices playerServices;

    @Override
    public Player login(Credential credential) {
        if(credential.getIdentity() instanceof UserNameIdentity) {
            return loginWithIdentity((UserNameIdentity) credential.getIdentity(), credential.getPassword());
        } else {
            return loginWithIdentity((EmailIdentity) credential.getIdentity(), credential.getPassword());
        }
    }

    @Override
    public Player login(CredentialV2 credential) {
        Player player = playerServices.getPlayerByUserNameOrEmailAndPassword(credential.getIdentity(), credential.getPassword());
        return updateLastLogin(player);
    }

    private Player loginWithIdentity(UserNameIdentity userNameIdentity, String password) {
        Player player = playerServices.getPlayerByUserNameAndPassword(userNameIdentity.getUserName(), password);
        return updateLastLogin(player);
    }

    private Player loginWithIdentity(EmailIdentity emailIdentity, String password) {
        Player player = playerServices.getPlayerByEmailAndPassword(emailIdentity.getEmail(), password);
        return updateLastLogin(player);
    }

    private Player updateLastLogin(Player player) {
        player.setLastLoginDate(LocalDateTime.now());
        return playerServices.savePlayer(player);
    }
}
