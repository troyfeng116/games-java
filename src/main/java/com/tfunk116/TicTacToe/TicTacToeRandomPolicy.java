package com.tfunk116.TicTacToe;

import com.tfunk116.Game.Policy.Policy;

// TODO: this is the same for all games. How to use generics to pass to any game player
public enum TicTacToeRandomPolicy implements Policy<TicTacToeAction> {
    INSTANCE;
}
