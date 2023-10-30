package com.tfunk116.SinglePlayer.Game.Visitors;

import com.tfunk116.SinglePlayer.Java2048.Java2048State;

public enum SinglePlayerInputStringVisitor implements SinglePlayerGameStateVisitor<String> {
    INSTANCE;

    private SinglePlayerInputStringVisitor() {
    };

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
