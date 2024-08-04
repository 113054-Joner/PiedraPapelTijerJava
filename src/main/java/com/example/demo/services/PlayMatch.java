package com.example.demo.services;


import com.example.demo.models.Match;
import com.example.demo.models.Play;
import org.springframework.stereotype.Service;

/**
 *
 * @param <P> The Play response
 * @param <M> The Match to play
 */
@Service
public interface PlayMatch<P extends Play, M extends Match> {

    P play(P play, M match);
}
