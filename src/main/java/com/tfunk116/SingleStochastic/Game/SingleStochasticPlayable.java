package com.tfunk116.SingleStochastic.Game;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Playable.PlayableGame;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.SingleStochastic.Game.GameState.SingleStochasticGameState;

public abstract class SingleStochasticPlayable<A extends Action>
        implements PlayableGame<A, SingleStochasticGameState<A>> {
    private final Player<A, SingleStochasticGameState<A>> thePlayer;

    public SingleStochasticPlayable(Player<A, SingleStochasticGameState<A>> aPlayer) {
        thePlayer = aPlayer;
    }

    public Player<A, SingleStochasticGameState<A>> getPlayer() {
        return thePlayer;
    }
}