package com.tfunk116.SinglePlayer.Game.GameState;

import java.util.List;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Player.Player;

public abstract class SinglePlayerStochasticGame<A extends Action> extends SinglePlayerGameState<A> {
    public SinglePlayerStochasticGame(Player<A, SinglePlayerGameState<A>> aCurrentActor) {
        super(aCurrentActor);
    }

    public abstract List<SinglePlayerGameState<A>> getSuccessors(A aAction)
            throws IllegalGameActionException, IllegalGameStateException;

    // TODO: non-uniform distribution
    @Override
    public final SinglePlayerGameState<A> getSuccessor(A aAction)
            throws IllegalGameActionException, IllegalGameStateException {
        List<SinglePlayerGameState<A>> mySuccessors = getSuccessors(aAction);
        return mySuccessors.get((int) Math.floor(Math.random() * mySuccessors.size()));
    }
}
