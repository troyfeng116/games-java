package com.tfunk116.SinglePlayer.Java2048;

import com.tfunk116.SinglePlayer.Game.SinglePlayerPlayable;
import com.tfunk116.SinglePlayer.Game.Player.SinglePlayerImpl;

public class PlayableJava2048 extends SinglePlayerPlayable<Java2048Action> {
    public PlayableJava2048(SinglePlayerImpl<Java2048Action> aPlayer) {
        super(aPlayer);
    }

    @Override
    public Java2048State getInitialState() {
        return new Java2048State(getPlayer());
    }
}