package com.tfunk116.SinglePlayer.Game.GameState;

import java.util.List;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;
import com.tfunk116.Game.Player.Player;

public abstract class SinglePlayerStochasticGame<A extends Action> extends GameState<A, SinglePlayerStochasticGame<A>> {
    public SinglePlayerStochasticGame(Player<A, SinglePlayerStochasticGame<A>> aCurrentActor) {
        super(aCurrentActor);
    }

    public abstract List<SinglePlayerStochasticGame<A>> getSuccessors(A aAction)
            throws IllegalGameActionException, IllegalGameStateException;

    // TODO: non-uniform distribution
    @Override
    public final SinglePlayerStochasticGame<A> getSuccessor(A aAction)
            throws IllegalGameActionException, IllegalGameStateException {
        List<SinglePlayerStochasticGame<A>> mySuccessors = getSuccessors(aAction);
        return mySuccessors.get((int) Math.floor(Math.random() * mySuccessors.size()));
    }
}
