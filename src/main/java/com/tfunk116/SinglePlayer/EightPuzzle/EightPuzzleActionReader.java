package com.tfunk116.SinglePlayer.EightPuzzle;

import com.tfunk116.Game.Action.ActionReader;
import com.tfunk116.Game.Visitors.GameStateDumpVisitor;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;

public enum EightPuzzleActionReader
        implements ActionReader<EightPuzzleAction, SinglePlayerGameState<EightPuzzleAction>> {
    INSTANCE;

    @Override
    public EightPuzzleAction readAction(SinglePlayerGameState<EightPuzzleAction> aState) {
        System.out.println(aState.accept(GameStateDumpVisitor.INSTANCE));

        System.out.print("Input idx: ");
        int myInput = IN_SCANNER.nextInt();
        return new EightPuzzleAction(myInput);
    }
}
