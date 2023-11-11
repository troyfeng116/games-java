package com.tfunk116.Game.Playable;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Visitors.GameReportVisitor;
import com.tfunk116.Game.Visitors.GameStateDumpVisitor;

public interface PlayableGame<A extends Action, G extends GameState<A, G>> {
    G getInitialState() throws IllegalGameStateException;

    default double playThroughGame()
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        G myCurState = getInitialState();
        while (!myCurState.isTerminal()) {
            Player<A, G> myCurPlayer = myCurState.getCurrentActor();
            A mySelectedAction = myCurPlayer.selectAction(myCurState);
            myCurState = myCurState.getSuccessor(mySelectedAction);
        }

        System.out.println(myCurState.accept(GameStateDumpVisitor.INSTANCE));
        System.out.println(myCurState.accept(GameReportVisitor.INSTANCE));
        return myCurState.getPayoff();
    }
}
