package com.tfunk116.Game.Visitors;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.SinglePlayer.EightPuzzle.EightPuzzleState;
import com.tfunk116.SingleStochastic.Java2048.Java2048State;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeAction;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeState;

public enum GameReportVisitor implements GameStateVisitor<String> {
    INSTANCE;

    @Override
    public String visit(EightPuzzleState aState) {
        return baseVisit(aState);
    }

    @Override
    public String visit(Java2048State aState) {
        return baseVisit(aState);
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

    private <A extends Action, G extends GameState<A, G>> String baseVisit(G aState) {
        try {
            double myPayoff = aState.getPayoff();
            return String.format("========\nFinal score: %f\n========\n", myPayoff);
        } catch (IllegalGamePayoffException myException) {
            return String.format("Game not yet complete: %s\n");
        }
    }
}
