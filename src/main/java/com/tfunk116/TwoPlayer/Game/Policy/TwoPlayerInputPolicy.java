package com.tfunk116.TwoPlayer.Game.Policy;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Action.ActionReader;
import com.tfunk116.Game.Policy.InputPolicy;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;

public class TwoPlayerInputPolicy<A extends Action> extends InputPolicy<A, TwoPlayerGameState<A>>
        implements TwoPlayerPolicy<A> {
    public TwoPlayerInputPolicy(ActionReader<A, TwoPlayerGameState<A>> aActionReader) {
        super(aActionReader);
    }
}
