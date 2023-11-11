package com.tfunk116.SinglePlayer.Game;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Playable.PlayableGame;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerStochasticGame;

public abstract class SinglePlayerStochasticPlayable<A extends Action>
        implements PlayableGame<A, SinglePlayerStochasticGame<A>> {
    private final Player<A, SinglePlayerStochasticGame<A>> thePlayer;

    public SinglePlayerStochasticPlayable(Player<A, SinglePlayerStochasticGame<A>> aPlayer) {
        thePlayer = aPlayer;
    }

    public Player<A, SinglePlayerStochasticGame<A>> getPlayer() {
        return thePlayer;
    }
}