package com.tfunk116.TwoPlayer.Game.Heuristic;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;

public interface Heuristic<A extends Action> {
    double evaluate(TwoPlayerGameState<A> aState);
}
