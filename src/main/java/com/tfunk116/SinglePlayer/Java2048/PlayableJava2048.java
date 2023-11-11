package com.tfunk116.SinglePlayer.Java2048;

import com.tfunk116.Game.Player.Player;
import com.tfunk116.SinglePlayer.Game.SinglePlayerStochasticPlayable;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerStochasticGame;

public class PlayableJava2048 extends SinglePlayerStochasticPlayable<Java2048Action> {
    public PlayableJava2048(Player<Java2048Action, SinglePlayerStochasticGame<Java2048Action>> aPlayer) {
        super(aPlayer);
    }

    @Override
    public Java2048State getInitialState() {
        return new Java2048State(getPlayer());
    }
}