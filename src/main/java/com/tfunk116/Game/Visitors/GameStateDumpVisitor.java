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

        // TODO: add getGrid() helper to reuse 8Puzzle/TTT
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
        int[][] myBoard = aState.getBoard();
        int myBoardSize = aState.getBoardSize();

        StringBuilder myBuilder = new StringBuilder();

        String myDelim = getDelim(myBoardSize * 9);
        myBuilder.append(myDelim);
        myBuilder.append(String.format("Score: %f\n\n", aState.getPayoff()));

        String myHorizontalEdge = getGridHorizontalEdge(0, myBoardSize * 6);
        myBuilder.append(myHorizontalEdge);

        String myEmptyRow = getGridEmptyRow(0, myBoardSize * 6);
        for (int myR = 0; myR < myBoardSize; myR++) {
            myBuilder.append('|');
            for (int myC = 0; myC < myBoardSize; myC++) {
                myBuilder.append(padInt(myBoard[myR][myC], 6));
            }
            myBuilder.append("|\n");
            if (myR != myBoardSize - 1) {
                myBuilder.append(myEmptyRow);
            }
        }
        myBuilder.append(myHorizontalEdge);

        myBuilder.append('\n');
        myBuilder.append(myDelim);
        myBuilder.append('\n');
        return myBuilder.toString();
    }

    private static String getDelim(int aWidth) {
        return times('=', aWidth) + "\n";
    }

    /**
     * Build string of column labels in grid strings, consisting of `aFrontSpaces`
     * spaces, followed by each column index from `1` to `aMaxCol` inclusive with
     * one space before and after the index, ending with a newline. Total length
     * `aFrontSpaces` + 3 * aMaxCol`, not including newline.
     */
    private static String getColLabelRow(int aFrontSpaces, int aMaxCol) {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append(times(' ', aFrontSpaces));
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
        myBuilder.append(times(' ', aFrontSpaces));
        myBuilder.append('x');
        myBuilder.append(times('-', aWidth));
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
        myBuilder.append(times(' ', aFrontSpaces));
        myBuilder.append('|');
        myBuilder.append(times(' ', aWidth));
        myBuilder.append("|\n");
        return myBuilder.toString();
    }

    /**
     * Given integer, return string repetition, padded evenly on left/right sides to
     * `aWidth`.
     */
    private static String padInt(int aVal, int aWidth) {
        StringBuilder myPaddedIntBuilder = new StringBuilder();
        String myValAsString = String.valueOf(aVal);
        int myTotalPadding = aWidth - myValAsString.length();
        int myPadding = myTotalPadding / 2;
        myPaddedIntBuilder.append(times(' ', myPadding + (myTotalPadding % 2)));
        myPaddedIntBuilder.append(myValAsString);
        myPaddedIntBuilder.append(times(' ', myPadding));
        return myPaddedIntBuilder.toString();
    }

    private static String times(char aCh, int aReps) {
        StringBuilder myBuilder = new StringBuilder();
        for (int myI = 0; myI < aReps; myI++) {
            myBuilder.append(aCh);
        }
        return myBuilder.toString();
    }
}
