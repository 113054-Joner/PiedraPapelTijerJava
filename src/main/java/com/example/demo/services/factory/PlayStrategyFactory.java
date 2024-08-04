package com.example.demo.services.factory;


import com.example.demo.models.rps.MatchRps;
import com.example.demo.models.rps.PlayRps;
import com.example.demo.services.PlayMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayStrategyFactory {

    @Autowired
    private PlayMatch<PlayRps, MatchRps> playMatchRps;

    public PlayMatch getPlayStrategy(String gameCode) {
        switch (gameCode) {
            case "RPS":
                return playMatchRps;
            default:
                return playMatchRps;
        }
    }
}
