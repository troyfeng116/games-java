package com.tfunk116.Game.Policy;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Action.ActionReader;
import com.tfunk116.Game.GameState.GameState;

public abstract class InputPolicy<A extends Action, G extends GameState<A, G>> implements Policy<A, G> {
    private final ActionReader<A, G> theActionReader;

    public InputPolicy(ActionReader<A, G> aActionReader) {
        theActionReader = aActionReader;
    }

    @Override
    public final A selectAction(G aState) {
        A myAction;
        do {
            myAction = theActionReader.readAction(aState);
        } while (myAction == null || !aState.isLegalAction(myAction));

        return myAction;
    }
}
