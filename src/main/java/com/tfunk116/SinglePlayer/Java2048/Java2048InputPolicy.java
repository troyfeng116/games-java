package com.tfunk116.SinglePlayer.Java2048;

import java.util.Scanner;

import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;
import com.tfunk116.SinglePlayer.Game.Policy.SinglePlayerPolicy;
import com.tfunk116.SinglePlayer.Game.Visitors.SinglePlayerInputStringVisitor;

public class Java2048InputPolicy implements SinglePlayerPolicy<Java2048Action> {
    private static Java2048Action promptJava2048Action(SinglePlayerGameState<Java2048Action> aState, Scanner aScanner) {
        System.out.println(aState.accept(SinglePlayerInputStringVisitor.INSTANCE));

        System.out.print("Input w/a/s/d: ");
        String myInput = aScanner.nextLine();
        if (myInput.equals("w")) {
            return Java2048Action.UP;
        }
        if (myInput.equals("d")) {
            return Java2048Action.RIGHT;
        }
        if (myInput.equals("s")) {
            return Java2048Action.DOWN;
        }
        if (myInput.equals("a")) {
            return Java2048Action.LEFT;
        }
        return null;
    }

    @Override
    public Java2048Action selectAction(SinglePlayerGameState<Java2048Action> aState) {
        Scanner myScanner = new Scanner(System.in);
        Java2048Action myAction;
        do {
            myAction = promptJava2048Action(aState, myScanner);
        } while (myAction == null || !aState.isLegalAction(myAction));

        return myAction;
    }
}
