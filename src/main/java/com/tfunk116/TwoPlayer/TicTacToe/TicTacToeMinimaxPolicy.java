package com.tfunk116.TwoPlayer.TicTacToe;

import java.util.List;

import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.TwoPlayer.Game.Policy.TwoPlayerPolicy;

public class TicTacToeMinimaxPolicy implements TwoPlayerPolicy<TicTacToeAction> {

    /**
     * Search game state tree to find max guaranteed (min) P1 points for max player,
     * or min guaranteed (max) P1 points for min player.
     *
     * @param aState
     * @return
     * @throws IllegalGamePayoffException
     * @throws IllegalGameStateException
     * @throws IllegalGameActionException
     */
    private double minimax(TwoPlayerGameState<TicTacToeAction> aState)
            throws IllegalGamePayoffException, IllegalGameActionException, IllegalGameStateException {
        if (aState.isTerminal()) {
            return aState.getMaxPlayerPayoff();
        }

        List<TicTacToeAction> myActions = aState.getLegalActions();
        if (aState.isMaxPlayerTurn()) {
            double myMaxPayoff = Double.MIN_VALUE;
            for (TicTacToeAction myAction : myActions) {
                myMaxPayoff = Math.max(myMaxPayoff, minimax(aState.getSuccessor(myAction)));
            }
            return myMaxPayoff;
        }

        double myMinPayoff = Double.MAX_VALUE;
        for (TicTacToeAction myAction : myActions) {
            myMinPayoff = Math.min(myMinPayoff, minimax(aState.getSuccessor(myAction)));
        }
        return myMinPayoff;
    }

    @Override
    public TicTacToeAction selectAction(TwoPlayerGameState<TicTacToeAction> aState) {
        try {
            List<TicTacToeAction> myActions = aState.getLegalActions();
            TicTacToeAction myBestAction = TicTacToeRandomPolicy.INSTANCE.selectAction(aState);

            if (aState.isMaxPlayerTurn()) {
                double myMaxPayoff = Double.MIN_VALUE;
                for (TicTacToeAction myAction : myActions) {
                    double mySearchRes = minimax(aState.getSuccessor(myAction));
                    if (mySearchRes > myMaxPayoff) {
                        myMaxPayoff = mySearchRes;
                        myBestAction = myAction;
                    }
                }
                return myBestAction;
            }

            double myMinPayoff = Double.MAX_VALUE;
            for (TicTacToeAction myAction : myActions) {
                double mySearchRes = minimax(aState.getSuccessor(myAction));
                if (mySearchRes < myMinPayoff) {
                    myMinPayoff = mySearchRes;
                    myBestAction = myAction;
                }
            }
            return myBestAction;
        } catch (Exception e) {
            return TicTacToeRandomPolicy.INSTANCE.selectAction(aState);
        }
    }
}
