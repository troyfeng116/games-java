package com.tfunk116.Game.Visitors;

import com.tfunk116.SinglePlayer.EightPuzzle.EightPuzzleState;
import com.tfunk116.SingleStochastic.Java2048.Java2048State;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeState;

public interface GameStateVisitor<T> {
    public T visit(EightPuzzleState aState);

    public T visit(Java2048State aState);

    public T visit(TicTacToeState aState);

}
