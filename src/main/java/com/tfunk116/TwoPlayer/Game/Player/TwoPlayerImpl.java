package com.tfunk116.TwoPlayer.Game.Player;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;
import com.tfunk116.TwoPlayer.Game.Policy.TwoPlayerPolicy;

public class TwoPlayerImpl<A extends Action> extends Player<A, TwoPlayerGameState<A>> {
    public TwoPlayerImpl(String aName, TwoPlayerPolicy<A> aPolicy) {
        super(aName, aPolicy);
    }
}
