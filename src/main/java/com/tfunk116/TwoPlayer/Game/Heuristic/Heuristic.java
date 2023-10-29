package com.tfunk116.TwoPlayer.Game.Heuristic;

import com.tfunk116.TwoPlayer.Game.Action.Action;
import com.tfunk116.TwoPlayer.Game.GameState.GameState;

public interface Heuristic<A extends Action> {
    double evaluate(GameState<A> aState);
}
