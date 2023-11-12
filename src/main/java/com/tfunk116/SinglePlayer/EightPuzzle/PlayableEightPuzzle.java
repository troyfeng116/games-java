package com.tfunk116.SinglePlayer.EightPuzzle;

import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;
import com.tfunk116.SinglePlayer.Game.Playable.SinglePlayerPlayable;

public class PlayableEightPuzzle extends SinglePlayerPlayable<EightPuzzleAction> {
    public PlayableEightPuzzle(Player<EightPuzzleAction, SinglePlayerGameState<EightPuzzleAction>> aPlayer) {
        super(aPlayer);
    }

    @Override
    public SinglePlayerGameState<EightPuzzleAction> getInitialState() throws IllegalGameStateException {
        return new EightPuzzleState(getPlayer());
    }
}
