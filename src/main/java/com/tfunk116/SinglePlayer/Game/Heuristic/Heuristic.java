package com.tfunk116.SinglePlayer.Game.Heuristic;

import com.tfunk116.SinglePlayer.Game.Action.Action;
import com.tfunk116.SinglePlayer.Game.GameState.GameState;

public interface Heuristic<A extends Action> {
    double evaluate(GameState<A> aState);
}
