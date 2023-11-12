package com.tfunk116.Game.Heuristic;

import com.tfunk116.Game.Visitors.GameStateVisitor;
import com.tfunk116.SingleStochastic.Java2048.Java2048State;
import com.tfunk116.TwoPlayer.TicTacToe.TicTacToeState;

public interface Heuristic extends GameStateVisitor<Double> {
    @Override
    default Double visit(TicTacToeState aState) {
        return 0.0;
    }

    @Override
    default Double visit(Java2048State aState) {
        return 0.0;
    }
}
