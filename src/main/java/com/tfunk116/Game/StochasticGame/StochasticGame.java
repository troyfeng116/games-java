package com.tfunk116.Game.StochasticGame;

import java.util.List;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;
import com.tfunk116.Game.Player.Player;

public abstract class StochasticGame<A extends Action, G extends GameState<A, G>> extends GameState<A, G> {
    public StochasticGame(Player<A, G> aCurrentActor) {
        super(aCurrentActor);
    }

    public abstract List<? extends G> getSuccessors(A aAction)
            throws IllegalGameActionException, IllegalGameStateException;

    @Override
    public G getSuccessor(A aAction) throws IllegalGameActionException, IllegalGameStateException {
        List<? extends G> mySuccessors = getSuccessors(aAction);
        return mySuccessors.get((int) Math.floor(Math.random() * mySuccessors.size()));
    }
}
