package com.tfunk116.Game.Player;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;
import com.tfunk116.Game.Policy.Policy;

public abstract class Player<A extends Action, G extends GameState<A, G>> {
    private final String theName;
    private final Policy<A, G> thePolicy;

    public Player(String aName, Policy<A, G> aPolicy) {
        theName = aName;
        thePolicy = aPolicy;
    }

    public String getName() {
        return theName;
    }

    public Policy<A, G> getPolicy() {
        return thePolicy;
    }

    public A selectAction(G aGameState) {
        return thePolicy.selectAction(aGameState);
    }
}
