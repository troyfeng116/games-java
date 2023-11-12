package com.tfunk116.TwoPlayer.TicTacToe;

import com.tfunk116.Game.Action.ActionReader;
import com.tfunk116.Game.Visitors.GameStateDumpVisitor;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;

public enum TicTacToeActionReader implements ActionReader<TicTacToeAction, TwoPlayerGameState<TicTacToeAction>> {
    INSTANCE;

    private TicTacToeActionReader() {
    }

    @Override
    public TicTacToeAction readAction(TwoPlayerGameState<TicTacToeAction> aState) {
        System.out.println(aState.accept(GameStateDumpVisitor.INSTANCE));

        System.out.print("Input row: ");
        int myRow = IN_SCANNER.nextInt();
        System.out.print("Input column: ");
        int myCol = IN_SCANNER.nextInt();

        return new TicTacToeAction(myRow - 1, myCol - 1);
    }
}
