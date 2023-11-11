package com.tfunk116.SinglePlayer.Java2048;

import java.util.HashSet;
import java.util.Set;

import com.tfunk116.Game.Heuristic.Heuristic;

public class Java2048CornerHeuristic implements Heuristic {
    @Override
    public Double visit(Java2048State aState) {
        Set<Java2048Action> myActions = new HashSet<>(aState.getLegalActions());
        if (!myActions.contains(Java2048Action.UP) && !myActions.contains(Java2048Action.LEFT)) {
            return -10.0;
        }

        int[][] myBoard = aState.getBoard();
        int myBoardSize = myBoard.length;
        int myMaxValue = 0;
        for (int myR = 0; myR < myBoardSize; myR++) {
            for (int myC = 0; myC < myBoardSize; myC++) {
                myMaxValue = Math.max(myMaxValue, myBoard[myR][myC]);
            }
        }

        if (myBoard[0][0] != myMaxValue) {
            return -1.0;
        }

        for (int myR = 0; myR < myBoardSize - 1; myR++) {
            for (int myC = 1; myC < myBoardSize; myC++) {
                if (myBoard[myR][myC - 1] < myBoard[myR][myC] || myBoard[myR][myC] < myBoard[myR + 1][myC]) {
                    return (double) myR;
                }
            }
        }

        return (double) 10.0;
    }
}