package com.tfunk116.SinglePlayer.Game.Policy;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Policy.Policy;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;

public interface SinglePlayerPolicy<A extends Action> extends Policy<A, SinglePlayerGameState<A>> {
}
