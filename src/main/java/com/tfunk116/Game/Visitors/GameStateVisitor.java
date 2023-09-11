package com.tfunk116.Game.Visitors;

import com.tfunk116.TicTacToe.TicTacToeState;

public interface GameStateVisitor<T> {
    public T visit(TicTacToeState aState);
}
