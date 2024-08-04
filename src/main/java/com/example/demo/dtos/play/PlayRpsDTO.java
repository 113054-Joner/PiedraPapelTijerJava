package com.example.demo.dtos.play;


import com.example.demo.models.rps.ShapeHand;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayRpsDTO implements PlayRequest {

    @NotNull
    @JsonProperty("shape_hand_player_1")
    private ShapeHand shapeHandPlayer1;

    @JsonProperty("shape_hand_player_2")
    private ShapeHand shapeHandPlayer2;
}
