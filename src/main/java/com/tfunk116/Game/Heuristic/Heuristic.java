package com.tfunk116.Game.Heuristic;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;

public interface Heuristic<A extends Action> {
    double evaluate(GameState<A> aState);
}
