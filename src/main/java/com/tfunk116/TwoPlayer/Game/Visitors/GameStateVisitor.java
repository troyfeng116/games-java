package com.tfunk116.TwoPlayer.Game.Visitors;

import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeState;

public interface GameStateVisitor<T> {
    public T visit(TicTacToeState aState);
}
