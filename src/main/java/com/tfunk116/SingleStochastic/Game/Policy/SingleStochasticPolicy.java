package com.tfunk116.SingleStochastic.Game.Policy;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Policy.Policy;
import com.tfunk116.SingleStochastic.Game.GameState.SingleStochasticGameState;

public interface SingleStochasticPolicy<A extends Action> extends Policy<A, SingleStochasticGameState<A>> {
}
