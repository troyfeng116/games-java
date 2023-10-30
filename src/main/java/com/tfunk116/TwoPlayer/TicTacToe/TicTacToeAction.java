package com.tfunk116.TwoPlayer.TicTacToe;

import com.tfunk116.Game.Action.Action;

public class TicTacToeAction implements Action {
    private final int theRow;
    private final int theCol;

    public TicTacToeAction(int aRow, int aCol) {
        theRow = aRow;
        theCol = aCol;
    }

    public int getRow() {
        return theRow;
    }

    public int getCol() {
        return theCol;
    }
}
