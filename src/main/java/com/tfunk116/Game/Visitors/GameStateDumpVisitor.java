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

        myBuilder.append(getColLabelRow(3, myBoardSize));

        String myHorizontalEdge = getGridHorizontalEdge(2, myBoardSize * 3);
        myBuilder.append(myHorizontalEdge);

        String myEmptyRow = getGridEmptyRow(2, myBoardSize * 3);
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
        myBuilder.append(myHorizontalEdge);

        return String.format("==================\n\n%s\nmoves taken: %d\n", myBuilder.toString(),
                aState.getMovesTaken());
    }

    @Override
    public String visit(TicTacToeState aState) {
        TicTacToeCell[][] myBoard = aState.getBoard();
        int myBoardSize = aState.getBoardSize();
        StringBuilder myBuilder = new StringBuilder();

        String myDelim = getDelim(myBoardSize * 6);
        myBuilder.append(myDelim);
        myBuilder.append(String.format("current turn: %s\n\n", aState.getCurrentActor().getName()));

        myBuilder.append(getColLabelRow(3, myBoardSize));

        String myHorizontalEdge = getGridHorizontalEdge(2, myBoardSize * 3);
        myBuilder.append(myHorizontalEdge);

        String myEmptyRow = getGridEmptyRow(2, myBoardSize * 3);
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

        myBuilder.append(myHorizontalEdge);
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

    private static String getDelim(int aWidth) {
        StringBuilder myDelimBuilder = new StringBuilder();
        for (int myI = 0; myI < aWidth; myI++) {
            myDelimBuilder.append('=');
        }
        myDelimBuilder.append('\n');
        return myDelimBuilder.toString();
    }

    /**
     * Build string of column labels in grid strings, consisting of `aFrontSpaces`
     * spaces, followed by each column index from `1` to `aMaxCol` inclusive with
     * one space before and after the index, ending with a newline. Total length
     * `aFrontSpaces` + 3 * aMaxCol`, not including newline.
     */
    private static String getColLabelRow(int aFrontSpaces, int aMaxCol) {
        StringBuilder myBuilder = new StringBuilder();
        for (int myI = 0; myI < aFrontSpaces; myI++) {
            myBuilder.append(' ');
        }
        for (int myC = 1; myC <= aMaxCol; myC++) {
            myBuilder.append(' ');
            myBuilder.append(myC);
            myBuilder.append(' ');
        }
        myBuilder.append('\n');
        return myBuilder.toString();
    }

    /**
     * Build string consisting of `aFrontSpaces` spaces, a start corner marker `x`,
     * `aWidth` horizontal edge markers `-`, and an end corner marker `x`, ending
     * with newline. Total length `aFrontSpaces + 2 + aWidth`, not including
     * newline.
     */
    private static String getGridHorizontalEdge(int aFrontSpaces, int aWidth) {
        StringBuilder myBuilder = new StringBuilder();
        for (int myI = 0; myI < aFrontSpaces; myI++) {
            myBuilder.append(' ');
        }
        myBuilder.append('x');
        for (int myI = 0; myI < aWidth; myI++) {
            myBuilder.append('-');
        }
        myBuilder.append("x\n");
        return myBuilder.toString();
    }

    /**
     * Build string consisting of `aFrontSpaces` spaces, a start column marker `|`,
     * `aWidth` spaces, and an end column marker `|`, ending with newline. Total
     * length `aFrontSpaces + 2 + aWidth`, not including newline.
     */
    private static String getGridEmptyRow(int aFrontSpaces, int aWidth) {
        StringBuilder myBuilder = new StringBuilder();
        for (int myI = 0; myI < aFrontSpaces; myI++) {
            myBuilder.append(' ');
        }
        myBuilder.append('|');
        for (int myI = 0; myI < aWidth; myI++) {
            myBuilder.append(' ');
        }
        myBuilder.append("|\n");
        return myBuilder.toString();
    }
}
