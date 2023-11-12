package com.tfunk116.SingleStochastic.Game.GameState;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;
import com.tfunk116.Game.Player.Player;

public abstract class SingleStochasticGameState<A extends Action> extends GameState<A, SingleStochasticGameState<A>> {
    public SingleStochasticGameState(Player<A, SingleStochasticGameState<A>> aCurrentActor) {
        super(aCurrentActor);
    }

    /**
     * Return possible distribution of stochastic successor game states.
     *
     * @param aAction Game action for which to retrieve successor states.
     * @return List of successor states, with probability distributions.
     * @throws IllegalGameActionException
     * @throws IllegalGameStateException
     */
    public abstract List<SimpleEntry<? extends SingleStochasticGameState<A>, Double>> getSuccessors(A aAction)
            throws IllegalGameActionException, IllegalGameStateException;

    @Override
    public final SingleStochasticGameState<A> getSuccessor(A aAction)
            throws IllegalGameActionException, IllegalGameStateException {
        List<SimpleEntry<? extends SingleStochasticGameState<A>, Double>> mySuccessors = getSuccessors(aAction);
        double myRand = Math.random();
        double myCumulativeSum = 0.0;
        for (int myIdx = 0; myIdx < mySuccessors.size(); myIdx++) {
            if (myCumulativeSum <= myRand && myRand < (myCumulativeSum += mySuccessors.get(myIdx).getValue())) {
                return mySuccessors.get(myIdx).getKey();
            }
        }
        return mySuccessors.get((int) Math.floor(Math.random() * mySuccessors.size())).getKey();
    }
}
