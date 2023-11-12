package com.tfunk116.SingleStochastic.Game.Player;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Policy.Policy;
import com.tfunk116.SingleStochastic.Game.GameState.SingleStochasticGameState;

public class SingleStochasticPlayerImpl<A extends Action> extends Player<A, SingleStochasticGameState<A>> {
    public SingleStochasticPlayerImpl(String aName, Policy<A, SingleStochasticGameState<A>> aPolicy) {
        super(aName, aPolicy);
    }
}