package com.tfunk116.SinglePlayer.EightPuzzle;

import com.tfunk116.Game.Action.Action;

public class EightPuzzleAction implements Action {
    private final int theIdx;

    public EightPuzzleAction(int aIdx) {
        theIdx = aIdx;
    }

    public int getIdx() {
        return theIdx;
    }
}
