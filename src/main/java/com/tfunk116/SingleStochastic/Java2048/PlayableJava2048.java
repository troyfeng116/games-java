package com.tfunk116.SingleStochastic.Java2048;

import com.tfunk116.Game.Player.Player;
import com.tfunk116.SingleStochastic.Game.SingleStochasticPlayable;
import com.tfunk116.SingleStochastic.Game.GameState.SingleStochasticGameState;

public class PlayableJava2048 extends SingleStochasticPlayable<Java2048Action> {
    public PlayableJava2048(Player<Java2048Action, SingleStochasticGameState<Java2048Action>> aPlayer) {
        super(aPlayer);
    }

    @Override
    public Java2048State getInitialState() {
        return new Java2048State(getPlayer());
    }
}