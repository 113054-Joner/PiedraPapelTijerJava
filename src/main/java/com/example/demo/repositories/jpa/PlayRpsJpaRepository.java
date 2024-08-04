package com.example.demo.repositories.jpa;


import com.example.demo.entities.PlayRpsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRpsJpaRepository extends JpaRepository<PlayRpsEntity, Long> {
}
