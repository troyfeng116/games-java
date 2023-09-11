package com.tfunk116.TicTacToe;

import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Policy.Policy;

public class TicTacToeMain {
    public static void main(String[] args)
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        Policy<TicTacToeAction> myPlayer1Policy = new TicTacToeInputPolicy();
        Player<TicTacToeAction> myPlayer1 = new Player<>("Troy", myPlayer1Policy);
        Policy<TicTacToeAction> myPlayer2Policy = new TicTacToeInputPolicy();
        Player<TicTacToeAction> myPlayer2 = new Player<>("Computer", myPlayer2Policy);
        PlayableTicTacToe myPlayableTicTacToe = new PlayableTicTacToe(myPlayer1, myPlayer2, 3);
        myPlayableTicTacToe.playThroughGame();
    }
}
