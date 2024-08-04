package com.example.demo.services.factory;


import com.example.demo.dtos.play.PlayRequest;
import com.example.demo.dtos.play.PlayRpsDTO;
import com.example.demo.models.Play;
import com.example.demo.models.rps.PlayRps;

public class PlayFactory {

    public static Play getPlayInstance(PlayRequest playRequest, String gameCode) {
        switch (gameCode) {
            case "RPS":
                return getPlayRpsInstance(playRequest);
            default:
                return getPlayRpsInstance(playRequest);
        }
    }

    private static Play getPlayRpsInstance(PlayRequest playRequest) {
        PlayRpsDTO playRpsDTO = (PlayRpsDTO) playRequest;
        PlayRps playRps = new PlayRps();
        playRps.setShapeHandPlayer1(playRpsDTO.getShapeHandPlayer1());
        playRps.setShapeHandPlayer2(playRpsDTO.getShapeHandPlayer2());
        return playRps;
    }
}
