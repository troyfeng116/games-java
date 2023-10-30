package com.tfunk116.TwoPlayer.Game.GameState;

import com.tfunk116.Game.GameState.GameState;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Action.Action;

public abstract class TwoPlayerGameState<A extends Action> extends GameState<A, TwoPlayerGameState<A>>
        implements TwoPlayerGameStateVisitable {
    private final Player<A, TwoPlayerGameState<A>> theMaxPlayer;
    private final Player<A, TwoPlayerGameState<A>> theMinPlayer;

    public TwoPlayerGameState(Player<A, TwoPlayerGameState<A>> aMaxPlayer, Player<A, TwoPlayerGameState<A>> aMinPlayer,
            boolean isMaxPlayerTurn) {
        super(isMaxPlayerTurn ? aMaxPlayer : aMinPlayer);

        theMaxPlayer = aMaxPlayer;
        theMinPlayer = aMinPlayer;
    }

    public abstract double getMaxPlayerPayoff() throws IllegalGamePayoffException;

    // TODO: incremental scores at non-terminal states
    @Override
    public final double getPayoff() {
        try {
            return getMaxPlayerPayoff();
        } catch (IllegalGamePayoffException myException) {
            return 0.0;
        }
    }

    public final Player<A, TwoPlayerGameState<A>> getMaxPlayer() {
        return theMaxPlayer;
    }

    public final Player<A, TwoPlayerGameState<A>> getMinPlayer() {
        return theMinPlayer;
    }

    public final boolean isMaxPlayerTurn() {
        return getCurrentActor().equals(getMaxPlayer());
    }
}
