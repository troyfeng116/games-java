package com.tfunk116.TwoPlayer.TicTacToe;

import java.util.Arrays;

import com.tfunk116.TwoPlayer.Game.TwoPlayerPlayable;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Player.Player;

public class PlayableTicTacToe extends TwoPlayerPlayable<TicTacToeAction> {
    private final int theBoardSize;

    public PlayableTicTacToe(Player<TicTacToeAction, TwoPlayerGameState<TicTacToeAction>> aMaxPlayer,
            Player<TicTacToeAction, TwoPlayerGameState<TicTacToeAction>> aMinPlayer,
            int aBoardSize) {
        super(aMaxPlayer, aMinPlayer);

        theBoardSize = aBoardSize;
    }

    @Override
    public TicTacToeState getInitialState() throws IllegalGameStateException {
        TicTacToeCell[][] myInitialBoard = new TicTacToeCell[theBoardSize][theBoardSize];
        for (var myRow : myInitialBoard) {
            Arrays.fill(myRow, TicTacToeCell.EMPTY);
        }
        return new TicTacToeState(getMaxPlayer(), getMinPlayer(), true, theBoardSize, myInitialBoard);
    }
}
