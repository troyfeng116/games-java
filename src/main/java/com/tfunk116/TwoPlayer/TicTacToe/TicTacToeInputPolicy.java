package com.tfunk116.TwoPlayer.TicTacToe;

import java.util.Scanner;

import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;
import com.tfunk116.TwoPlayer.Game.Policy.TwoPlayerPolicy;
import com.tfunk116.TwoPlayer.Game.Visitors.TwoPlayerInputStringVisitor;

public class TicTacToeInputPolicy implements TwoPlayerPolicy<TicTacToeAction> {
    private static TicTacToeAction promptTicTacToeAction(TwoPlayerGameState<TicTacToeAction> aState, Scanner aScanner) {
        System.out.println(aState.accept(TwoPlayerInputStringVisitor.INSTANCE));

        System.out.print("Input row: ");
        int myRow = aScanner.nextInt();
        System.out.print("Input column: ");
        int myCol = aScanner.nextInt();

        return new TicTacToeAction(myRow - 1, myCol - 1);
    }

    @Override
    public TicTacToeAction selectAction(TwoPlayerGameState<TicTacToeAction> aState) {
        Scanner myScanner = new Scanner(System.in);
        TicTacToeAction myAction;
        do {
            myAction = promptTicTacToeAction(aState, myScanner);
        } while (!aState.isLegalAction(myAction));

        return myAction;
    }
}
