package com.tfunk116.SinglePlayer.Java2048;

import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.SinglePlayer.Game.Player.SinglePlayerImpl;
import com.tfunk116.SinglePlayer.Game.Policy.SinglePlayerPolicy;

public class Java2048Main {
    public static void main(String[] args)
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        SinglePlayerPolicy<Java2048Action> myInputPolicy = new Java2048InputPolicy();
        SinglePlayerImpl<Java2048Action> myInputPlayer = new SinglePlayerImpl<>("troy", myInputPolicy);
        PlayableJava2048 myPlayableJava2048 = new PlayableJava2048(myInputPlayer);
        myPlayableJava2048.playThroughGame();
    }
}
