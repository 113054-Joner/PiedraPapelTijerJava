package com.example.demo.services;


import com.example.demo.dtos.login.Credential;
import com.example.demo.dtos.login.CredentialV2;

import com.example.demo.models.Player;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    Player login(Credential credential);

    Player login(CredentialV2 credential);
}
