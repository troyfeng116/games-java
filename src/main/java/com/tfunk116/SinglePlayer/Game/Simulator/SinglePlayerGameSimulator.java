package com.tfunk116.SinglePlayer.Game.Simulator;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Simulator.PlayerSimStatistics;
import com.tfunk116.Game.Simulator.Simulator;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;
import com.tfunk116.SinglePlayer.Game.Playable.SinglePlayerPlayable;

public class SinglePlayerGameSimulator<A extends Action>
        extends Simulator<A, SinglePlayerGameState<A>, SinglePlayerPlayable<A>> {
    private final PlayerSimStatistics theSimStatistics;

    public SinglePlayerGameSimulator(int aNumSimulations, SinglePlayerPlayable<A> aPlayableGame) {
        super(aNumSimulations, aPlayableGame);
        theSimStatistics = new PlayerSimStatistics(aPlayableGame.getPlayer().getName());
    }

    @Override
    public void runSimulations()
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        System.out.printf("Running %d simulations...\n", getNumSimulations());
        for (int myTrial = 0; myTrial < getNumSimulations(); myTrial++) {
            double mySimPayoff = getPlayable().playThroughGame();
            theSimStatistics.updateTotalPayoff(mySimPayoff);
        }
        System.out.println(theSimStatistics.getSimReport());
    }
}
