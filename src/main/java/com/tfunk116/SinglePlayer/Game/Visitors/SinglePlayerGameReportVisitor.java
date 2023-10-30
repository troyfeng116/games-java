package com.tfunk116.SinglePlayer.Game.Visitors;

import com.tfunk116.SinglePlayer.Java2048.Java2048State;

public enum SinglePlayerGameReportVisitor implements SinglePlayerGameStateVisitor<String> {
    INSTANCE;

    @Override
    public String visit(Java2048State aState) {
        if (!aState.isTerminal()) {
            return "Game not yet complete";
        }

        double myPayoff = aState.getPayoff();
        return String.format("========\nFinal score: %f\n========\n", myPayoff);
    }
}
