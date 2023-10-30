package com.tfunk116.SinglePlayer.Java2048;

import com.tfunk116.SinglePlayer.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.SinglePlayer.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.SinglePlayer.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.SinglePlayer.Game.Player.Player;
import com.tfunk116.SinglePlayer.Game.Policy.Policy;

public class Java2048Main {
    public static void main(String[] args)
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        Policy<Java2048Action> myInputPolicy = new Java2048InputPolicy();
        Player<Java2048Action> myInputPlayer = new Player<>("troy", myInputPolicy);
        PlayableJava2048 myPlayableJava2048 = new PlayableJava2048(myInputPlayer);
        myPlayableJava2048.playThroughGame();
    }
}
