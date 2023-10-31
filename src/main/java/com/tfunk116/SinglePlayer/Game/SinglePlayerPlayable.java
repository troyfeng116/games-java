package com.tfunk116.SinglePlayer.Game;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Playable.PlayableGame;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.SinglePlayer.Game.Visitors.SinglePlayerGameReportVisitor;

/*
 * Game.init()
 * Fields:
 * - game state
 *   - getLegalActions()
 *   - getCurPlayer()
 * - two players: each has a policy
 * - policy takes state and returns action
 */

public abstract class SinglePlayerPlayable<A extends Action> implements PlayableGame<A, SinglePlayerGameState<A>> {
    private final Player<A, SinglePlayerGameState<A>> thePlayer;

    public SinglePlayerPlayable(Player<A, SinglePlayerGameState<A>> aPlayer) {
        thePlayer = aPlayer;
    }

    public Player<A, SinglePlayerGameState<A>> getPlayer() {
        return thePlayer;
    }

    @Override
    public double playThroughGame()
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        SinglePlayerGameState<A> myCurState = getInitialState();
        while (!myCurState.isTerminal()) {
            Player<A, SinglePlayerGameState<A>> myCurPlayer = myCurState.getCurrentActor();
            A mySelectedAction = myCurPlayer.selectAction(myCurState);
            myCurState = myCurState.getSuccessor(mySelectedAction);
        }

        System.out.println(myCurState.accept(SinglePlayerGameReportVisitor.INSTANCE));
        return myCurState.getPayoff();
    }
}