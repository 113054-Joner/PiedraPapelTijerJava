package com.example.demo.models.rps;



import com.example.demo.models.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRps extends Match {

    private Integer numberOfPlays;
    private Integer remainderPlays;
    private Integer player1Score;
    private Integer player2Score;
    private List<PlayRps> plays;
    private Long winnerId;
}
