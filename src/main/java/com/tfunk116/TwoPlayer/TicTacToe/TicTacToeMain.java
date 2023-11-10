package com.tfunk116.TwoPlayer.TicTacToe;

import com.tfunk116.TwoPlayer.Game.TwoPlayerGameSimulator;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.TwoPlayer.Game.Player.TwoPlayerImpl;
import com.tfunk116.TwoPlayer.Game.Policy.TwoPlayerMinimaxPolicy;
import com.tfunk116.TwoPlayer.Game.Policy.TwoPlayerPolicy;
import com.tfunk116.TwoPlayer.Game.Policy.TwoPlayerRandomPolicy;

public class TicTacToeMain {
    public static void main(String[] args)
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        // Policy<TicTacToeAction> myPlayer1Policy = new TwoPlayerMinimaxPolicy<>();
        TwoPlayerPolicy<TicTacToeAction> myPlayer1Policy = new TwoPlayerRandomPolicy<>();
        TwoPlayerImpl<TicTacToeAction> myPlayer1 = new TwoPlayerImpl<>("Troy", myPlayer1Policy);
        TwoPlayerPolicy<TicTacToeAction> myPlayer2Policy = new TwoPlayerMinimaxPolicy<>();
        // Policy<TicTacToeAction> myPlayer2Policy = new TwoPlayerRandomPolicy<>();
        TwoPlayerImpl<TicTacToeAction> myPlayer2 = new TwoPlayerImpl<>("Computer", myPlayer2Policy);
        PlayableTicTacToe myPlayableTicTacToe = new PlayableTicTacToe(myPlayer1, myPlayer2, 3);
        // myPlayableTicTacToe.playThroughGame();

        TwoPlayerGameSimulator<TicTacToeAction> mySimulator = new TwoPlayerGameSimulator<>(10, myPlayableTicTacToe);
        mySimulator.runSimulations();
    }
}
