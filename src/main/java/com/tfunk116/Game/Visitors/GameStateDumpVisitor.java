package com.tfunk116.Game.Visitors;

import com.tfunk116.SinglePlayer.EightPuzzle.EightPuzzleState;
import com.tfunk116.SingleStochastic.Java2048.Java2048State;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeCell;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeState;

public enum GameStateDumpVisitor implements GameStateVisitor<String> {
    INSTANCE;

    private GameStateDumpVisitor() {
    };

    @Override
    public String visit(EightPuzzleState aState) {
        int[] myBoard = aState.getBoard();
        int myBoardSize = EightPuzzleState.getBoardSize();
        StringBuilder myBuilder = new StringBuilder();

        myBuilder.append("   ");
        for (int myC = 1; myC <= myBoardSize; myC++) {
            myBuilder.append(' ');
            myBuilder.append(myC);
            myBuilder.append(' ');
        }
        myBuilder.append('\n');

        StringBuilder myEdge = new StringBuilder();
        myEdge.append("  ");
        myEdge.append('x');
        for (int myI = 0; myI < myBoardSize * 3; myI++) {
            myEdge.append('-');
        }
        myEdge.append("x\n");

        StringBuilder myEmptyRow = new StringBuilder();
        myEmptyRow.append("  ");
        myEmptyRow.append('|');
        for (int myI = 0; myI < myBoardSize * 3; myI++) {
            myEmptyRow.append(' ');
        }
        myEmptyRow.append("|\n");

        myBuilder.append(myEdge);
        for (int myR = 1; myR <= myBoardSize; myR++) {
            myBuilder.append(myR);
            myBuilder.append(' ');
            myBuilder.append('|');
            for (int myC = 0; myC < myBoardSize; myC++) {
                int myIdx = (myR - 1) * myBoardSize + myC;
                myBuilder.append(' ');
                if (EightPuzzleState.isEmpty(myBoard[myIdx])) {
                    myBuilder.append('_');
                } else {
                    myBuilder.append(myBoard[myIdx]);
                }
                myBuilder.append(' ');
            }
            myBuilder.append("|\n");
            if (myR != myBoardSize) {
                myBuilder.append(myEmptyRow);
            }
        }
        myBuilder.append(myEdge);

        return String.format("==================\n\n%s\nmoves taken: %d\n", myBuilder.toString(),
                aState.getMovesTaken());
    }

    @Override
    public String visit(TicTacToeState aState) {
        TicTacToeCell[][] myBoard = aState.getBoard();
        int myBoardSize = aState.getBoardSize();
        StringBuilder myBuilder = new StringBuilder();

        StringBuilder myDelim = new StringBuilder();
        for (int myI = 0; myI <= myBoardSize * 6; myI++) {
            myDelim.append('=');
        }
        myBuilder.append(myDelim);
        myBuilder.append('\n');

        myBuilder.append(String.format("current turn: %s\n\n", aState.getCurrentActor().getName()));

        myBuilder.append("   ");
        for (int myCol = 1; myCol <= myBoardSize; myCol++) {
            myBuilder.append(' ');
            myBuilder.append(myCol);
            myBuilder.append(' ');
        }
        myBuilder.append('\n');

        StringBuilder myEdge = new StringBuilder();
        myEdge.append("  ");
        myEdge.append('x');
        for (int myI = 0; myI < myBoardSize * 3; myI++) {
            myEdge.append('-');
        }
        myEdge.append("x\n");
        myBuilder.append(myEdge);

        StringBuilder myEmptyRow = new StringBuilder();
        myEmptyRow.append("  ");
        myEmptyRow.append('|');
        for (int myI = 0; myI < myBoardSize * 3; myI++) {
            myEmptyRow.append(' ');
        }
        myEmptyRow.append("|\n");

        for (int myRow = 0; myRow < myBoardSize; myRow++) {
            myBuilder.append(myRow + 1);
            myBuilder.append(" |");
            for (int myCol = 0; myCol < myBoardSize; myCol++) {
                myBuilder.append(' ');
                myBuilder.append(switch (myBoard[myRow][myCol]) {
                    case MAX_PLAYER -> 'X';
                    case MIN_PLAYER -> 'O';
                    case EMPTY -> '_';
                });
                myBuilder.append(' ');
            }
            myBuilder.append("|\n");
            if (myRow != myBoardSize - 1) {
                myBuilder.append(myEmptyRow);
            }
        }

        myBuilder.append(myEdge);
        myBuilder.append('\n');
        myBuilder.append(myDelim);
        myBuilder.append('\n');

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
