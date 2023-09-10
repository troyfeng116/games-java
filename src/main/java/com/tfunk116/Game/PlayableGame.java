package com.tfunk116.Game;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.State.GameState;
import com.tfunk116.Game.State.GameState.IllegalGameActionException;
import com.tfunk116.Game.State.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.State.GameState.IllegalGameStateException;

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
    private final Player<A> theMaxPlayer;
    private final Player<A> theMinPlayer;

    public PlayableGame(Player<A> aMaxPlayer, Player<A> aMinPlayer) {
        theMaxPlayer = aMaxPlayer;
        theMinPlayer = aMinPlayer;
    }

    public Player<A> getMaxPlayer() {
        return theMaxPlayer;
    }

    public Player<A> getMinPlayer() {
        return theMinPlayer;
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

        return myCurState.getMaxPlayerPayoff();
    }
}