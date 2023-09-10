package com.tfunk116.TicTacToe;

import java.util.Arrays;

import com.tfunk116.Game.PlayableGame;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.State.GameState.IllegalGameStateException;

public class PlayableTicTacToe extends PlayableGame<TicTacToeAction> {
    private final int theBoardSize;

    public PlayableTicTacToe(Player<TicTacToeAction> aMaxPlayer, Player<TicTacToeAction> aMinPlayer, int aBoardSize) {
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
