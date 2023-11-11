package com.tfunk116.TwoPlayer.Game.Policy;

import java.util.List;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;

public class TwoPlayerMinimaxPolicy<A extends Action> implements TwoPlayerPolicy<A> {
    @Override
    public A selectAction(TwoPlayerGameState<A> aState) {
        // TODO: have minimax return pair?
        try {
            List<A> myActions = aState.getLegalActions();
            A myBestAction = null;

            if (aState.isMaxPlayerTurn()) {
                double myMaxPayoff = Double.MIN_VALUE;
                for (A myAction : myActions) {
                    double mySearchRes = minimax(aState.getSuccessor(myAction));
                    if (mySearchRes > myMaxPayoff) {
                        myMaxPayoff = mySearchRes;
                        myBestAction = myAction;
                    }
                }
                return myBestAction;
            }

            double myMinPayoff = Double.MAX_VALUE;
            for (A myAction : myActions) {
                double mySearchRes = minimax(aState.getSuccessor(myAction));
                if (mySearchRes < myMinPayoff) {
                    myMinPayoff = mySearchRes;
                    myBestAction = myAction;
                }
            }
            return myBestAction;
        } catch (Exception e) {
            return null;
        }
    }

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
    private double minimax(TwoPlayerGameState<A> aState)
            throws IllegalGamePayoffException, IllegalGameActionException, IllegalGameStateException {
        if (aState.isTerminal()) {
            return aState.getMaxPlayerPayoff();
        }

        List<A> myActions = aState.getLegalActions();
        if (aState.isMaxPlayerTurn()) {
            double myMaxPayoff = Double.MIN_VALUE;
            for (A myAction : myActions) {
                myMaxPayoff = Math.max(myMaxPayoff, minimax(aState.getSuccessor(myAction)));
            }
            return myMaxPayoff;
        }

        double myMinPayoff = Double.MAX_VALUE;
        for (A myAction : myActions) {
            myMinPayoff = Math.min(myMinPayoff, minimax(aState.getSuccessor(myAction)));
        }
        return myMinPayoff;
    }
}
