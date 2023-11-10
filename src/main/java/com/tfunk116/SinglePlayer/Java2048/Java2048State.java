package com.tfunk116.SinglePlayer.Java2048;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Visitors.GameStateVisitor;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;

public class Java2048State extends SinglePlayerGameState<Java2048Action> {
    private final static int EMPTY = 0;
    private final static int BOARD_SIZE = 4;

    private int theScore;
    private final int[][] theBoard;

    public Java2048State(Player<Java2048Action, SinglePlayerGameState<Java2048Action>> aPlayer) {
        super(aPlayer);

        theScore = 0;
        theBoard = new int[BOARD_SIZE][BOARD_SIZE];

        int myStartR1 = getRandomIdx(BOARD_SIZE), myStartC1 = getRandomIdx(BOARD_SIZE);
        int myStartR2 = getRandomIdx(BOARD_SIZE), myStartC2 = getRandomIdx(BOARD_SIZE);
        while (myStartR2 == myStartR1) {
            myStartR2 = getRandomIdx(BOARD_SIZE);
        }
        while (myStartC2 == myStartC1) {
            myStartC2 = getRandomIdx(BOARD_SIZE);
        }
        theBoard[myStartR1][myStartC1] = 2;
        theBoard[myStartR2][myStartC2] = 2;
    }

    private Java2048State(Player<Java2048Action, SinglePlayerGameState<Java2048Action>> aPlayer, int aScore,
            int[][] aBoard) {
        super(aPlayer);
        theScore = aScore;
        theBoard = aBoard;
    }

    public int[][] getBoard() {
        return theBoard;
    }

    @Override
    public boolean isLegalAction(Java2048Action aMove) {
        if (aMove == Java2048Action.DOWN || aMove == Java2048Action.UP) {
            boolean myShouldReverse = aMove == Java2048Action.DOWN;
            for (int myC = 0; myC < BOARD_SIZE; myC++) {
                int[] myCol = extractCol(myC, myShouldReverse);
                if (canDimensionMove(myCol)) {
                    return true;
                }
            }
        } else {
            boolean myShouldReverse = aMove == Java2048Action.RIGHT;
            for (int myR = 0; myR < BOARD_SIZE; myR++) {
                int[] myRow = extractRow(myR, myShouldReverse);
                if (canDimensionMove(myRow)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Java2048Action> getLegalActions() {
        return List.of(Java2048Action.UP, Java2048Action.RIGHT, Java2048Action.DOWN, Java2048Action.LEFT).stream()
                .filter(aMove -> isLegalAction(aMove)).toList();
    }

    @Override
    public boolean isTerminal() {
        return getLegalActions().size() == 0;
    }

    @Override
    public Java2048State getSuccessor(Java2048Action aMove) {
        if (!isLegalAction(aMove)) {
            return null;
        }

        int myDScore = 0;
        if (aMove == Java2048Action.DOWN || aMove == Java2048Action.UP) {
            boolean myShouldReverse = aMove == Java2048Action.DOWN;
            for (int myC = 0; myC < BOARD_SIZE; myC++) {
                int[] myCol = extractCol(myC, myShouldReverse);
                myDScore += siftDimension(myCol);
                writeToCol(myC, myCol, myShouldReverse);
            }
        } else {
            boolean myShouldReverse = aMove == Java2048Action.RIGHT;
            for (int myR = 0; myR < BOARD_SIZE; myR++) {
                int[] myRow = extractRow(myR, myShouldReverse);
                myDScore += siftDimension(myRow);
                writeToRow(myR, myRow, myShouldReverse);
            }
        }

        List<SimpleEntry<Integer, Integer>> myEmptyTiles = getEmptyTiles();
        SimpleEntry<Integer, Integer> myRandomEmptyTile = myEmptyTiles.get(getRandomIdx(myEmptyTiles.size()));
        theBoard[myRandomEmptyTile.getKey()][myRandomEmptyTile.getValue()] = getNewSquareValue();

        theScore += myDScore;
        return new Java2048State(getCurrentActor(), theScore, theBoard);
    }

    @Override
    public double getPayoff() {
        return theScore;
    }

    @Override
    public <T> T accept(GameStateVisitor<T> aVisitor) {
        return aVisitor.visit(this);
    }

    private boolean canDimensionMove(int[] aDim) {
        int myM = 0;
        for (int myIdx = 0; myIdx < BOARD_SIZE; myIdx++) {
            if (aDim[myIdx] != EMPTY) {
                if (myIdx != myM) {
                    return true;
                }
                if (myM > 0 && aDim[myM - 1] == aDim[myM]) {
                    return true;
                }
                myM++;
            }
        }
        return false;
    }

    private int siftDimension(int[] aDim) {
        int myDPoints = 0;
        int myM = 0;
        int myLastMerged = -1;
        for (int myIdx = 0; myIdx < BOARD_SIZE; myIdx++) {
            if (aDim[myIdx] != EMPTY) {
                int myTmp = aDim[myIdx];
                aDim[myIdx] = aDim[myM];
                aDim[myM] = myTmp;
                if (myM > 0 && aDim[myM - 1] == aDim[myM] && myM - 1 > myLastMerged) {
                    aDim[myM - 1] *= 2;
                    myDPoints += aDim[myM - 1];
                    aDim[myM] = EMPTY;
                    myLastMerged = myM - 1;
                } else {
                    myM++;
                }
            }
        }
        return myDPoints;
    }

    private int[] extractCol(int aC, boolean aShouldReverse) {
        int[] myCol = new int[BOARD_SIZE];
        for (int myR = 0; myR < BOARD_SIZE; myR++) {
            myCol[aShouldReverse ? BOARD_SIZE - myR - 1 : myR] = theBoard[myR][aC];
        }
        return myCol;
    }

    private void writeToCol(int aC, int[] aCol, boolean aShouldReverse) {
        for (int myR = 0; myR < BOARD_SIZE; myR++) {
            theBoard[myR][aC] = aCol[aShouldReverse ? BOARD_SIZE - myR - 1 : myR];
        }
    }

    private int[] extractRow(int aR, boolean aShouldReverse) {
        int[] myRow = new int[BOARD_SIZE];
        for (int myC = 0; myC < BOARD_SIZE; myC++) {
            myRow[aShouldReverse ? BOARD_SIZE - myC - 1 : myC] = theBoard[aR][myC];
        }
        return myRow;
    }

    private void writeToRow(int aR, int[] aRow, boolean aShouldReverse) {
        for (int myC = 0; myC < BOARD_SIZE; myC++) {
            theBoard[aR][myC] = aRow[aShouldReverse ? BOARD_SIZE - myC - 1 : myC];
        }
    }

    private int getRandomIdx(int aLen) {
        return (int) (Math.random() * aLen);
    }

    private int getNewSquareValue() {
        return Math.random() < 0.1 ? 4 : 2;
    }

    private List<SimpleEntry<Integer, Integer>> getEmptyTiles() {
        List<SimpleEntry<Integer, Integer>> myEmptyTiles = new ArrayList<>();
        for (int myR = 0; myR < BOARD_SIZE; myR++) {
            for (int myC = 0; myC < BOARD_SIZE; myC++) {
                if (theBoard[myR][myC] == EMPTY) {
                    myEmptyTiles.add(new SimpleEntry<>(myR, myC));
                }
            }
        }
        return myEmptyTiles;
    }
}
