package com.tfunk116.Game.Visitors;

import com.tfunk116.SinglePlayer.Java2048.Java2048State;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeState;

public interface GameStateVisitor<T> {
    public T visit(Java2048State aState);

    public T visit(TicTacToeState aState);
}
