package com.tfunk116.TwoPlayer.TicTacToe;

import com.tfunk116.TwoPlayer.Game.Policy.TwoPlayerPolicy;

// TODO: this is the same for all games. How to use generics to pass to any game player
public enum TicTacToeRandomPolicy implements TwoPlayerPolicy<TicTacToeAction> {
    INSTANCE;
}
