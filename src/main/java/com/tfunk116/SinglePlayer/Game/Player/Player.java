package com.tfunk116.SinglePlayer.Game.Player;

import com.tfunk116.SinglePlayer.Game.Action.Action;
import com.tfunk116.SinglePlayer.Game.GameState.GameState;
import com.tfunk116.SinglePlayer.Game.Policy.Policy;

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
