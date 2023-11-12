package com.tfunk116.SinglePlayer.Game.Policy;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Action.ActionReader;
import com.tfunk116.Game.Policy.InputPolicy;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;

public class SinglePlayerInputPolicy<A extends Action> extends InputPolicy<A, SinglePlayerGameState<A>>
        implements SinglePlayerPolicy<A> {
    public SinglePlayerInputPolicy(ActionReader<A, SinglePlayerGameState<A>> aActionReader) {
        super(aActionReader);
    }
}
