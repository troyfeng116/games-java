package com.tfunk116.Game.Visitors;

import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.SingleStochastic.Java2048.Java2048State;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeAction;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeState;

public enum GameReportVisitor implements GameStateVisitor<String> {
    INSTANCE;

    @Override
    public String visit(Java2048State aState) {
        if (!aState.isTerminal()) {
            return "Game not yet complete";
        }

        double myPayoff = aState.getPayoff();
        return String.format("========\nFinal score: %f\n========\n", myPayoff);
    }

    @Override
    public String visit(TicTacToeState aState) {
        try {
            double myPayoff = aState.getMaxPlayerPayoff();
            if (myPayoff == 0.0) {
                return String.format("========\nGame report: TIE\n========\n\n");
            }
            Player<TicTacToeAction, TwoPlayerGameState<TicTacToeAction>> myWinner = myPayoff == 1.0
                    ? aState.getMaxPlayer()
                    : aState.getMinPlayer();
            return String.format("========\nGame report: Player %s won\n========\n\n", myWinner.getName());
        } catch (IllegalGamePayoffException myException) {
            return String.format("Game not yet complete: %s\n");
        }
    }
}
