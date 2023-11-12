package com.tfunk116.SingleStochastic.Game.Policy;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Action.ActionReader;
import com.tfunk116.Game.Policy.InputPolicy;
import com.tfunk116.SingleStochastic.Game.GameState.SingleStochasticGameState;

public class SingleStochasticInputPolicy<A extends Action> extends InputPolicy<A, SingleStochasticGameState<A>>
        implements SingleStochasticPolicy<A> {
    public SingleStochasticInputPolicy(ActionReader<A, SingleStochasticGameState<A>> aActionReader) {
        super(aActionReader);
    }
}
