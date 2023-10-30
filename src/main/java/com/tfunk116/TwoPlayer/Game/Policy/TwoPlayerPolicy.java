package com.tfunk116.TwoPlayer.Game.Policy;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Policy.Policy;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;

public interface TwoPlayerPolicy<A extends Action> extends Policy<A, TwoPlayerGameState<A>> {
}
