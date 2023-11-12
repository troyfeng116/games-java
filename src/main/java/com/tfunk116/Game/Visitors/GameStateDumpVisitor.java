package com.tfunk116.Game.Visitors;

import com.tfunk116.SingleStochastic.Java2048.Java2048State;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeCell;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeState;

public enum GameStateDumpVisitor implements GameStateVisitor<String> {
    INSTANCE;

    private GameStateDumpVisitor() {
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

    @Override
    public String visit(Java2048State aState) {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append("\n========\n");
        myBuilder.append(String.format("Score: %f\n", aState.getPayoff()));
        int[][] myBoard = aState.getBoard();
        for (int myR = 0; myR < myBoard.length; myR++) {
            for (int myC = 0; myC < myBoard[0].length; myC++) {
                myBuilder.append(myBoard[myR][myC]);
                myBuilder.append(' ');
            }
            myBuilder.append('\n');
        }
        myBuilder.append('\n');
        return myBuilder.toString();
    }
}
