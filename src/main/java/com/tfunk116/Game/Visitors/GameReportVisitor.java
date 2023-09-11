package com.tfunk116.Game.Visitors;

import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.TicTacToe.TicTacToeAction;
import com.tfunk116.TicTacToe.TicTacToeState;

public enum GameReportVisitor implements GameStateVisitor<String> {
    INSTANCE;

    @Override
    public String visit(TicTacToeState aState) {
        try {
            double myPayoff = aState.getMaxPlayerPayoff();
            if (myPayoff == 0.0) {
                return String.format("========\nGame report: TIE\n========\n\n");
            }
            Player<TicTacToeAction> myWinner = myPayoff == 1.0 ? aState.getMaxPlayer() : aState.getMinPlayer();
            return String.format("========\nGame report: Player %s won\n========\n\n", myWinner.getName());
        } catch (IllegalGamePayoffException myException) {
            return String.format("Game not yet complete: %s\n");
        }
    }
}
