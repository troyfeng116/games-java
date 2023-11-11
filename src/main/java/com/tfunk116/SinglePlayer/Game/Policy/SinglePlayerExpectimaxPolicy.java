package com.tfunk116.SinglePlayer.Game.Policy;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Heuristic.Heuristic;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerStochasticGame;

public class SinglePlayerExpectimaxPolicy<A extends Action> implements SinglePlayerStochasticPolicy<A> {
    private final Heuristic theHeuristic;
    private final int theMaxDepth;

    public SinglePlayerExpectimaxPolicy(Heuristic aHeuristic, int aMaxDepth) {
        theHeuristic = aHeuristic;
        theMaxDepth = aMaxDepth;
    }

    @Override
    public A selectAction(SinglePlayerStochasticGame<A> aState) {
        try {
            return expectimax(aState, 0).getKey();
        } catch (Exception e) {
            return null;
        }
    }

    private SimpleEntry<A, Double> expectimax(SinglePlayerStochasticGame<A> aState, int aDepth)
            throws IllegalGamePayoffException, IllegalGameActionException, IllegalGameStateException {
        if (aState.isTerminal() || aDepth >= theMaxDepth) {
            // return new SimpleEntry<>(null, -10000.0);
            return new SimpleEntry<>(null, aState.accept(theHeuristic));
        }

        if (aDepth >= theMaxDepth) {
            return new SimpleEntry<>(null, aState.accept(theHeuristic));
        }

        List<A> myActions = aState.getLegalActions();
        SimpleEntry<A, Double> myBestAction = new SimpleEntry<>(null, Double.NEGATIVE_INFINITY);
        for (A myAction : myActions) {
            List<SimpleEntry<? extends SinglePlayerStochasticGame<A>, Double>> mySuccessors = aState
                    .getSuccessors(myAction);
            double myExpectedPayoff = 0.0;
            for (SimpleEntry<? extends SinglePlayerStochasticGame<A>, Double> myEntry : mySuccessors) {
                SinglePlayerStochasticGame<A> mySuccessor = myEntry.getKey();
                double myProb = myEntry.getValue();
                SimpleEntry<A, Double> myRes = expectimax(mySuccessor, aDepth + 1);
                if (myRes.getValue() != Double.NEGATIVE_INFINITY) {
                    myExpectedPayoff += myRes.getValue() * myProb;
                }
            }
            if (myExpectedPayoff > myBestAction.getValue()) {
                myBestAction = new SimpleEntry<>(myAction, myExpectedPayoff);
            }
        }

        return myBestAction;
    }
}
