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

        System.out.print("Input row: ");
        int myRow = IN_SCANNER.nextInt();
        System.out.print("Input col: ");
        int myCol = IN_SCANNER.nextInt();
        return new EightPuzzleAction((myRow - 1) * EightPuzzleState.getBoardSize() + myCol - 1);
    }
}
