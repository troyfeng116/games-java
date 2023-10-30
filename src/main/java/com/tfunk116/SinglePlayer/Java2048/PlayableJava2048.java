package com.tfunk116.SinglePlayer.Java2048;

import com.tfunk116.SinglePlayer.Game.PlayableGame;
import com.tfunk116.SinglePlayer.Game.Player.Player;

public class PlayableJava2048 extends PlayableGame<Java2048Action> {
    public PlayableJava2048(Player<Java2048Action> aPlayer) {
        super(aPlayer);
    }

    @Override
    public Java2048State getInitialState() {
        return new Java2048State(getPlayer());
    }
}