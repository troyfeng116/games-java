package com.tfunk116.SinglePlayer.Game.GameState;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;
import com.tfunk116.Game.Player.Player;

public abstract class SinglePlayerGameState<A extends Action> extends GameState<A, SinglePlayerGameState<A>> {
    public SinglePlayerGameState(Player<A, SinglePlayerGameState<A>> aPlayer) {
        super(aPlayer);
    }
}
