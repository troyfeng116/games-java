package com.tfunk116.SinglePlayer.Game.Heuristic;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;

public interface Heuristic<A extends Action> {
    double evaluate(SinglePlayerGameState<A> aState);
}
