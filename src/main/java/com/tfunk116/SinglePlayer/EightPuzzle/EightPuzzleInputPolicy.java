package com.tfunk116.SinglePlayer.EightPuzzle;

import java.util.Scanner;

import com.tfunk116.Game.Visitors.GameStateDumpVisitor;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;
import com.tfunk116.SinglePlayer.Game.Policy.SinglePlayerPolicy;

public class EightPuzzleInputPolicy implements SinglePlayerPolicy<EightPuzzleAction> {
    private static EightPuzzleAction promptEightPuzzleAction(SinglePlayerGameState<EightPuzzleAction> aState,
            Scanner aScanner) {
        System.out.println(aState.accept(GameStateDumpVisitor.INSTANCE));

        System.out.print("Input idx: ");
        int myInput = aScanner.nextInt();
        return new EightPuzzleAction(myInput);
    }

    @Override
    public EightPuzzleAction selectAction(SinglePlayerGameState<EightPuzzleAction> aState) {
        Scanner myScanner = new Scanner(System.in);
        EightPuzzleAction myAction;
        do {
            myAction = promptEightPuzzleAction(aState, myScanner);
        } while (myAction == null || !aState.isLegalAction(myAction));

        return myAction;
    }
}
