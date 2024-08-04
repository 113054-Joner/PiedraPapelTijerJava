package com.example.demo.services.impl;


import com.example.demo.entities.MatchEntity;
import com.example.demo.entities.MatchRpsEntity;
import com.example.demo.entities.PlayRpsEntity;
import com.example.demo.models.MatchStatus;
import com.example.demo.models.rps.MatchRps;
import com.example.demo.models.rps.PlayRps;
import com.example.demo.models.rps.ShapeHand;

import com.example.demo.repositories.jpa.MatchJpaRepository;
import com.example.demo.repositories.jpa.PlayRpsJpaRepository;
import com.example.demo.services.PlayMatch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Service
public class PlayMatchRpsImpl implements PlayMatch<PlayRps, MatchRps> {

    @Autowired
    private PlayRpsJpaRepository playRpsJpaRepository;

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private ModelMapper modelMapper;


    private Random random = new Random();

    @Override
    public PlayRps play(PlayRps playRps, MatchRps matchRps) {
        playRps.setMatchRpsId(matchRps.getId());
        if(Objects.isNull(playRps.getShapeHandPlayer2())) {
            playRps.setShapeHandPlayer2(getRamdomShapeHand());
        }
        evaluatePlay(playRps, matchRps);
        calculateMatchScore(playRps, matchRps);
        calculateMatchStatus(matchRps);
        matchRps.setUpdatedAt(LocalDateTime.now());
        PlayRpsEntity playRpsEntity = modelMapper.map(playRps, PlayRpsEntity.class);
        playRpsJpaRepository.save(playRpsEntity);
        MatchEntity matchEntity = modelMapper.map(matchRps, MatchRpsEntity.class);
        matchJpaRepository.save(matchEntity);
        return playRps;
    }

    private ShapeHand getRamdomShapeHand() {
        Integer randomIndex = random.nextInt(3);
        return ShapeHand.values()[randomIndex];
    }

    private void evaluatePlay(PlayRps playRps, MatchRps matchRps) {
        if(!isPlayTie(playRps)) {
            setWinner(playRps, matchRps);
        }
    }

    private Boolean isPlayTie(PlayRps playRps) {
        return playRps.getShapeHandPlayer1().equals(playRps.getShapeHandPlayer2());
    }

    private void setWinner(PlayRps playRps, MatchRps matchRps) {
        if(playRps.getShapeHandPlayer1().equals(ShapeHand.PAPER)) {
            if(playRps.getShapeHandPlayer2().equals(ShapeHand.ROCK)) {
                playRps.setWinnerId(matchRps.getPlayer1().getId());
            } else {
                playRps.setWinnerId(matchRps.getPlayer2().getId());
            }
        } else if(playRps.getShapeHandPlayer1().equals(ShapeHand.ROCK)) {
            if(playRps.getShapeHandPlayer2().equals(ShapeHand.SCISSORS)) {
                playRps.setWinnerId(matchRps.getPlayer1().getId());
            } else {
                playRps.setWinnerId(matchRps.getPlayer2().getId());
            }
        } else {
            if(playRps.getShapeHandPlayer2().equals(ShapeHand.PAPER)) {
                playRps.setWinnerId(matchRps.getPlayer1().getId());
            } else {
                playRps.setWinnerId(matchRps.getPlayer2().getId());
            }
        }
    }

    private void calculateMatchScore(PlayRps playRps, MatchRps matchRps) {
        if(Objects.nonNull(playRps.getWinnerId())) {
            if(playRps.getWinnerId().equals(matchRps.getPlayer1().getId())) {
                matchRps.setPlayer1Score(matchRps.getPlayer1Score() + 1);
            } else {
                matchRps.setPlayer2Score(matchRps.getPlayer2Score() + 1);
            }
        }
    }

    private void calculateMatchStatus(MatchRps matchRps) {
        matchRps.setRemainderPlays(matchRps.getRemainderPlays() - 1);
        if(matchRps.getRemainderPlays() == 0) {
            matchRps.setStatus(MatchStatus.FINISHED);
            if(!isMatchTie(matchRps)) {
                if(matchRps.getPlayer1Score() > matchRps.getPlayer2Score()) {
                    matchRps.setWinnerId(matchRps.getPlayer1().getId());
                } else {
                    matchRps.setWinnerId(matchRps.getPlayer2().getId());
                }
            }
        }
    }

    private Boolean isMatchTie(MatchRps matchRps) {
        return matchRps.getPlayer1Score().equals(matchRps.getPlayer2Score());
    }

}
