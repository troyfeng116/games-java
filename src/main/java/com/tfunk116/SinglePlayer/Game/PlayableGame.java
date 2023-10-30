package com.tfunk116.SinglePlayer.Game;

import com.tfunk116.SinglePlayer.Game.Action.Action;
import com.tfunk116.SinglePlayer.Game.GameState.GameState;
import com.tfunk116.SinglePlayer.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.SinglePlayer.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.SinglePlayer.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.SinglePlayer.Game.Player.Player;
import com.tfunk116.SinglePlayer.Game.Visitors.GameReportVisitor;

/*
 * Game.init()
 * Fields:
 * - game state
 *   - getLegalActions()
 *   - getCurPlayer()
 * - two players: each has a policy
 * - policy takes state and returns action
 */

public abstract class PlayableGame<A extends Action> {
    private final Player<A> thePlayer;

    public PlayableGame(Player<A> aPlayer) {
        thePlayer = aPlayer;
    }

    public Player<A> getPlayer() {
        return thePlayer;
    }

    public abstract GameState<A> getInitialState() throws IllegalGameStateException;

    public double playThroughGame()
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        GameState<A> myCurState = getInitialState();
        while (!myCurState.isTerminal()) {
            Player<A> myCurPlayer = myCurState.getCurrentActor();
            A mySelectedAction = myCurPlayer.selectAction(myCurState);
            myCurState = myCurState.getSuccessor(mySelectedAction);
        }

        System.out.println(myCurState.accept(GameReportVisitor.INSTANCE));
        return myCurState.getPayoff();
    }
}