package com.example.demo.repositories;

import com.example.demo.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity,Long> {
    Optional<PlayerEntity> findByUserNameOrEmail(String userName,String email);
}
