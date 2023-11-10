package com.tfunk116.TwoPlayer.Game;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Playable.PlayableGame;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Visitors.GameReportVisitor;

/*
 * Game.init()
 * Fields:
 * - game state
 *   - getLegalActions()
 *   - getCurPlayer()
 * - two players: each has a policy
 * - policy takes state and returns action
 */

public abstract class TwoPlayerPlayable<A extends Action> implements PlayableGame<A, TwoPlayerGameState<A>> {
    private final Player<A, TwoPlayerGameState<A>> theMaxPlayer;
    private final Player<A, TwoPlayerGameState<A>> theMinPlayer;

    public TwoPlayerPlayable(Player<A, TwoPlayerGameState<A>> aMaxPlayer,
            Player<A, TwoPlayerGameState<A>> aMinPlayer) {
        theMaxPlayer = aMaxPlayer;
        theMinPlayer = aMinPlayer;
    }

    public Player<A, TwoPlayerGameState<A>> getMaxPlayer() {
        return theMaxPlayer;
    }

    public Player<A, TwoPlayerGameState<A>> getMinPlayer() {
        return theMinPlayer;
    }

    @Override
    public double playThroughGame()
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        TwoPlayerGameState<A> myCurState = getInitialState();
        while (!myCurState.isTerminal()) {
            Player<A, TwoPlayerGameState<A>> myCurPlayer = myCurState.getCurrentActor();
            A mySelectedAction = myCurPlayer.selectAction(myCurState);
            myCurState = myCurState.getSuccessor(mySelectedAction);
        }

        System.out.println(myCurState.accept(GameReportVisitor.INSTANCE));
        return myCurState.getMaxPlayerPayoff();
    }
}