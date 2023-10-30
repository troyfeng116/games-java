package com.tfunk116.Game.Playable;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;

public interface PlayableGame<A extends Action, G extends GameState<A, G>> {
    G getInitialState() throws IllegalGameStateException;

    double playThroughGame() throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException;
}
