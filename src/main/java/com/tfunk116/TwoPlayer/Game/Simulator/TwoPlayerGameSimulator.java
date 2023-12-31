package com.tfunk116.TwoPlayer.Game.Simulator;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Simulator.PlayerSimStatistics;
import com.tfunk116.Game.Simulator.Simulator;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;
import com.tfunk116.TwoPlayer.Game.Playable.TwoPlayerPlayable;

public class TwoPlayerGameSimulator<A extends Action>
        extends Simulator<A, TwoPlayerGameState<A>, TwoPlayerPlayable<A>> {
    private final TwoPlayerSimStatistics theSimStatistics;

    public TwoPlayerGameSimulator(int aNumSimulations, TwoPlayerPlayable<A> aPlayableGame) {
        super(aNumSimulations, aPlayableGame);
        theSimStatistics = new TwoPlayerSimStatistics(aPlayableGame.getMaxPlayer().getName(),
                aPlayableGame.getMinPlayer().getName());
    }

    @Override
    public void runSimulations()
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        System.out.printf("Running %d simulations...\n", getNumSimulations());
        for (int myTrial = 0; myTrial < getNumSimulations(); myTrial++) {
            double myMaxSimResult = getPlayable().playThroughGame();
            double myMinSimResult = 0.0 - myMaxSimResult; // TODO: move total available points into const sum Game?
            theSimStatistics.recordResult(myMaxSimResult, myMinSimResult);
        }
        System.out.println(theSimStatistics.getTwoPlayerSimReport());
    }

    static class TwoPlayerSimStatistics {
        private final PlayerSimStatistics theMaxPlayerStatistics;
        private final PlayerSimStatistics theMinPlayerStatistics;

        TwoPlayerSimStatistics(String aMaxPlayerName, String aMinPlayerName) {
            theMaxPlayerStatistics = new PlayerSimStatistics(aMaxPlayerName);
            theMinPlayerStatistics = new PlayerSimStatistics(aMinPlayerName);
        }

        void recordResult(double aMaxPlayerPayoff, double aMinPlayerPayoff) {
            theMaxPlayerStatistics.updateTotalPayoff(aMaxPlayerPayoff);
            theMinPlayerStatistics.updateTotalPayoff(aMinPlayerPayoff);
        }

        String getTwoPlayerSimReport() {
            return String.format(
                    "%s\n\n%s\n", theMaxPlayerStatistics.getSimReport(), theMinPlayerStatistics.getSimReport());
        }
    }
}
