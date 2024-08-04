package com.example.demo.repositories.jpa;


import com.example.demo.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchEntity, Long> {

    Optional<List<MatchEntity>> getAllByPlayer1Id(Long playerId);
}
