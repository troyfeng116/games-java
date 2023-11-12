package com.tfunk116.SingleStochastic.Java2048;

import com.tfunk116.Game.Action.ActionReader;
import com.tfunk116.Game.Visitors.GameStateDumpVisitor;
import com.tfunk116.SingleStochastic.Game.GameState.SingleStochasticGameState;

public enum Java2048ActionReader implements ActionReader<Java2048Action, SingleStochasticGameState<Java2048Action>> {
    INSTANCE;

    @Override
    public Java2048Action readAction(SingleStochasticGameState<Java2048Action> aState) {
        System.out.println(aState.accept(GameStateDumpVisitor.INSTANCE));

        System.out.print("Input w/a/s/d: ");
        String myInput = IN_SCANNER.nextLine();
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
}
