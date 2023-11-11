package com.tfunk116.SinglePlayer.Java2048.Heuristics;

import com.tfunk116.Game.Heuristic.Heuristic;
import com.tfunk116.SinglePlayer.Java2048.Java2048State;

/**
 * @see https://cs229.stanford.edu/proj2016/report/NieHouAn-AIPlays2048-report.pdf
 */

public class Java2048WeightHeuristic implements Heuristic {
    private static final double[][] WEIGHT = new double[][] {
            { 4096, 1024, 256, 64 },
            { 1024, 256, 64, 16 },
            { 256, 64, 16, 4 },
            { 64, 16, 4, 1 },
    };
    // private static final double[][] WEIGHT = new double[][] {
    // { Math.pow(4, 15), Math.pow(4, 14), Math.pow(4, 13), Math.pow(4, 12) },
    // { Math.pow(4, 8), Math.pow(4, 9), Math.pow(4, 10), Math.pow(4, 11) },
    // { Math.pow(4, 7), Math.pow(4, 6), Math.pow(4, 5), Math.pow(4, 4) },
    // { Math.pow(4, 0), Math.pow(4, 1), Math.pow(4, 2), Math.pow(4, 3) },
    // };

    @Override
    public Double visit(Java2048State aState) {
        int[][] myBoard = aState.getBoard();
        int myBoardSize = myBoard.length;
        double myHeuristicValue = 0.0;
        for (int myR = 0; myR < myBoardSize; myR++) {
            for (int myC = 0; myC < myBoardSize; myC++) {
                myHeuristicValue += myBoard[myR][myC] * WEIGHT[myR][myC];
            }
        }
        return aState.getScore() * myHeuristicValue;
    }
}
