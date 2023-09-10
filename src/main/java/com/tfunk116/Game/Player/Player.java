package com.tfunk116.Game.Player;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Policy.Policy;
import com.tfunk116.Game.State.GameState;

public class Player<A extends Action> {
    private final String theName;
    private final Policy<A> thePolicy;

    public Player(String aName, Policy<A> aPolicy) {
        theName = aName;
        thePolicy = aPolicy;
    }

    public String getName() {
        return theName;
    }

    public A selectAction(GameState<A> aState) {
        return thePolicy.selectAction(aState);
    }
}
