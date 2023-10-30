package com.tfunk116.SinglePlayer.Player;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Policy.Policy;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;

public class SinglePlayerImpl<A extends Action> extends Player<A, SinglePlayerGameState<A>> {
    public SinglePlayerImpl(String aName, Policy<A, SinglePlayerGameState<A>> aPolicy) {
        super(aName, aPolicy);
    }
}
