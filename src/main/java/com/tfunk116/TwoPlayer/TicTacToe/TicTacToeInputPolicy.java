package com.tfunk116.TwoPlayer.TicTacToe;

import java.util.Scanner;

import com.tfunk116.TwoPlayer.Game.GameState.GameState;
import com.tfunk116.TwoPlayer.Game.Policy.Policy;
import com.tfunk116.TwoPlayer.Game.Visitors.InputStringVisitor;

public class TicTacToeInputPolicy implements Policy<TicTacToeAction> {
    private static TicTacToeAction promptTicTacToeAction(GameState<TicTacToeAction> aState, Scanner aScanner) {
        System.out.println(aState.accept(InputStringVisitor.INSTANCE));

        System.out.print("Input row: ");
        int myRow = aScanner.nextInt();
        System.out.print("Input column: ");
        int myCol = aScanner.nextInt();

        return new TicTacToeAction(myRow - 1, myCol - 1);
    }

    @Override
    public TicTacToeAction selectAction(GameState<TicTacToeAction> aState) {
        Scanner myScanner = new Scanner(System.in);
        TicTacToeAction myAction;
        do {
            myAction = promptTicTacToeAction(aState, myScanner);
        } while (!aState.isLegalAction(myAction));

        return myAction;
    }
}
