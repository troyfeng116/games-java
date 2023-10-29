package com.tfunk116.TwoPlayer.TicTacToe;

import com.tfunk116.TwoPlayer.Game.GameSimulator;
import com.tfunk116.TwoPlayer.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.TwoPlayer.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.TwoPlayer.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.TwoPlayer.Game.Player.Player;
import com.tfunk116.TwoPlayer.Game.Policy.Policy;

public class TicTacToeMain {
    public static void main(String[] args)
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        // Policy<TicTacToeAction> myPlayer1Policy = new TicTacToeMinimaxPolicy();
        Policy<TicTacToeAction> myPlayer1Policy = TicTacToeRandomPolicy.INSTANCE;
        Player<TicTacToeAction> myPlayer1 = new Player<>("Troy", myPlayer1Policy);
        Policy<TicTacToeAction> myPlayer2Policy = new TicTacToeMinimaxPolicy();
        // Policy<TicTacToeAction> myPlayer2Policy = TicTacToeRandomPolicy.INSTANCE;
        Player<TicTacToeAction> myPlayer2 = new Player<>("Computer", myPlayer2Policy);
        PlayableTicTacToe myPlayableTicTacToe = new PlayableTicTacToe(myPlayer1, myPlayer2, 3);
        // myPlayableTicTacToe.playThroughGame();

        GameSimulator<TicTacToeAction> mySimulator = new GameSimulator<>(100, myPlayableTicTacToe);
        mySimulator.runSimulations();
    }
}
