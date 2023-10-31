package com.tfunk116.Game.Simulator;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Playable.PlayableGame;

public abstract class Simulator<A extends Action, G extends GameState<A, G>, P extends PlayableGame<A, G>> {
    private final int theNumSimulations;
    private final P thePlayable;

    public Simulator(int aNumSimulations, P aPlayable) {
        theNumSimulations = aNumSimulations;
        thePlayable = aPlayable;
    }

    public abstract void runSimulations()
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException;

    public int getNumSimulations() {
        return theNumSimulations;
    }

    public P getPlayable() {
        return thePlayable;
    }
}
