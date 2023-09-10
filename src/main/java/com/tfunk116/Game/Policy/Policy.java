package com.tfunk116.Game.Policy;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.State.GameState;

public interface Policy<A extends Action> {
    A selectAction(GameState<A> aState);
}
