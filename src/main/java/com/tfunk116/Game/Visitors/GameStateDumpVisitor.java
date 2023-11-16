package com.tfunk116.Game.Visitors;

import java.util.function.Function;

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
        Integer[][] myBoard = aState.getBoardAsObjGrid();
        int myBoardSize = EightPuzzleState.getBoardSize();
        StringBuilder myBuilder = new StringBuilder();

        String myDelim = getDelim(myBoardSize * 6);
        myBuilder.append(myDelim);

        myBuilder.append(createGridRepresentation(myBoard, myBoardSize, (aVal) -> {
            int aCell = (int) aVal;
            return (EightPuzzleState.isEmpty(aCell)) ? '_' : aCell;
        }));

        myBuilder.append('\n');
        myBuilder.append(String.format("moves taken: %d\n", aState.getMovesTaken()));
        myBuilder.append(myDelim);
        myBuilder.append('\n');

        return myBuilder.toString();
    }

    @Override
    public String visit(TicTacToeState aState) {
        TicTacToeCell[][] myBoard = aState.getBoard();
        int myBoardSize = aState.getBoardSize();
        StringBuilder myBuilder = new StringBuilder();

        String myDelim = getDelim(myBoardSize * 6);
        myBuilder.append(myDelim);
        myBuilder.append(String.format("current turn: %s\n\n", aState.getCurrentActor().getName()));

        myBuilder.append(createGridRepresentation(myBoard, myBoardSize, (aVal) -> {
            TicTacToeCell myCell = (TicTacToeCell) aVal;
            return switch (myCell) {
                case MAX_PLAYER -> 'X';
                case MIN_PLAYER -> 'O';
                case EMPTY -> '_';
            };
        }));

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
     * Given square `aBoardSize`-by-`aBoardSize` grid from a game state, format
     * grid, with column and row indices, according to given mapping function.
     * 
     * TODO:
     * - handle row/col indices with >1 digit
     * - move space padding to mapping function, include mappingFnWidth
     * - with cellWidth param, add shouldIncludeRowColLabels to extend to 2048
     */
    private static String createGridRepresentation(Object[][] aBoard, int aBoardSize,
            Function<Object, Object> aCellMappingFunction) {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append(getColLabelRow(3, aBoardSize));

        String myHorizontalEdge = getGridHorizontalEdge(2, aBoardSize * 3);
        myBuilder.append(myHorizontalEdge);

        String myEmptyRow = getGridEmptyRow(2, aBoardSize * 3);
        for (int myRow = 0; myRow < aBoardSize; myRow++) {
            myBuilder.append(myRow + 1);
            myBuilder.append(" |");
            for (int myCol = 0; myCol < aBoardSize; myCol++) {
                myBuilder.append(' ');
                myBuilder.append(aCellMappingFunction.apply(aBoard[myRow][myCol]));
                myBuilder.append(' ');
            }
            myBuilder.append("|\n");
            if (myRow != aBoardSize - 1) {
                myBuilder.append(myEmptyRow);
            }
        }

        myBuilder.append(myHorizontalEdge);
        return myBuilder.toString();
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
