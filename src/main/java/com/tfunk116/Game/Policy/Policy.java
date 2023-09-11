package com.tfunk116.Game.Policy;

import java.util.List;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;

public interface Policy<A extends Action> {
    default A selectAction(GameState<A> aState) {
        List<A> myLegalActions = aState.getLegalActions();
        int myNumActions = myLegalActions.size();
        if (myNumActions == 0) {
            return null;
        }

        return myLegalActions.get((int) (Math.floor(Math.random() * myNumActions)));
    }
}
