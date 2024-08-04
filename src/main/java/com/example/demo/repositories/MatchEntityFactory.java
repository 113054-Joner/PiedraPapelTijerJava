package com.example.demo.repositories;


import com.example.demo.entities.MatchEntity;
import com.example.demo.entities.MatchRpsEntity;
import com.example.demo.models.Match;

public class MatchEntityFactory {

    public static Class<? extends MatchEntity> getTypeOfMatch(Match match) {
        switch (match.getGame().getCode()) {
            case "RPS":
                return MatchRpsEntity.class;
            default:
                return MatchRpsEntity.class;
        }
    }
}
