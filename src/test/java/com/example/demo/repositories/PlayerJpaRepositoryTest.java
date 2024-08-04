package com.example.demo.repositories;

import com.example.demo.entities.PlayerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerJpaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Test
    void findByUserNameOrEmailTest() {
        PlayerEntity playerEntity = new PlayerEntity();//Creo y cargo datos al entity dentro del Optional<>
        playerEntity.setEmail("email@email.com");
        playerEntity.setUserName("hmorais");
        playerEntity.setPassword("Password03#");

        entityManager.persist(playerEntity);//Guarda el entity
        entityManager.flush();//Hace que se garde en la H2 el dato

        Optional<PlayerEntity> playerResult = playerJpaRepository.findByUserNameOrEmail("hmorais","email@email.com");
        assertEquals("hmorais",playerResult.get().getUserName());
        assertEquals(playerEntity.getEmail(),playerResult.get().getEmail());
    }
}