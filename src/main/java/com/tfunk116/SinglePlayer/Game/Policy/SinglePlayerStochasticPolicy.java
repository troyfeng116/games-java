package com.tfunk116.SinglePlayer.Game.Policy;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Policy.Policy;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerStochasticGame;

public interface SinglePlayerStochasticPolicy<A extends Action> extends Policy<A, SinglePlayerStochasticGame<A>> {
}
