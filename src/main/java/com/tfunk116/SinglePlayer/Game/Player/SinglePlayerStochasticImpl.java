package com.tfunk116.SinglePlayer.Game.Player;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Policy.Policy;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerStochasticGame;

public class SinglePlayerStochasticImpl<A extends Action> extends Player<A, SinglePlayerStochasticGame<A>> {
    public SinglePlayerStochasticImpl(String aName, Policy<A, SinglePlayerStochasticGame<A>> aPolicy) {
        super(aName, aPolicy);
    }
}