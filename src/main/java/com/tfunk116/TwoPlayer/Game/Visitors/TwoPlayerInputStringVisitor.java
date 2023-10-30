package com.tfunk116.TwoPlayer.Game.Visitors;

import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeCell;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeState;

public enum TwoPlayerInputStringVisitor implements TwoPlayerGameStateVisitor<String> {
    INSTANCE;

    private TwoPlayerInputStringVisitor() {
    };

    @Override
    public String visit(TicTacToeState aState) {
        TicTacToeCell[][] myBoard = aState.getBoard();
        int myBoardSize = aState.getBoardSize();
        StringBuilder myBuilder = new StringBuilder();

        for (int myI = 0; myI <= myBoardSize * 3; myI++) {
            myBuilder.append('=');
        }
        myBuilder.append('\n');

        myBuilder.append(String.format("current turn: %s\n", aState.getCurrentActor().getName()));

        myBuilder.append(' ');
        for (int myCol = 1; myCol <= myBoardSize; myCol++) {
            myBuilder.append(' ');
            myBuilder.append(myCol);
        }
        myBuilder.append('\n');

        for (int myRow = 0; myRow < myBoardSize; myRow++) {
            myBuilder.append(myRow + 1);
            for (int myCol = 0; myCol < myBoardSize; myCol++) {
                myBuilder.append(' ');
                myBuilder.append(switch (myBoard[myRow][myCol]) {
                    case MAX_PLAYER -> 'X';
                    case MIN_PLAYER -> 'O';
                    case EMPTY -> '_';
                });
            }
            myBuilder.append('\n');
        }

        for (int myI = 0; myI < myBoardSize * 3; myI++) {
            myBuilder.append('=');
        }
        myBuilder.append("\n\n");

        return myBuilder.toString();
    }
}
