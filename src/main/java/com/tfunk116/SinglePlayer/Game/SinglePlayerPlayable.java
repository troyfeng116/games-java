package com.tfunk116.SinglePlayer.Game;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;
import com.tfunk116.Game.Playable.PlayableGame;
import com.tfunk116.Game.Player.Player;

public abstract class SinglePlayerPlayable<A extends Action> implements PlayableGame<A, SinglePlayerGameState<A>> {
    private final Player<A, SinglePlayerGameState<A>> thePlayer;

    public SinglePlayerPlayable(Player<A, SinglePlayerGameState<A>> aPlayer) {
        thePlayer = aPlayer;
    }

    public Player<A, SinglePlayerGameState<A>> getPlayer() {
        return thePlayer;
    }
}