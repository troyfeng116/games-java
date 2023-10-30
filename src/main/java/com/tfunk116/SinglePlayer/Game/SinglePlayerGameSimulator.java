package com.tfunk116.SinglePlayer.Game;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;

public class SinglePlayerGameSimulator<A extends Action> {
    private final int theNumSimulations;
    private final SinglePlayerPlayableGame<A> thePlayableGame;
    private final SimStatistics theSimStatistics;

    public SinglePlayerGameSimulator(int aNumSimulations, SinglePlayerPlayableGame<A> aPlayableGame) {
        theNumSimulations = aNumSimulations;
        thePlayableGame = aPlayableGame;
        theSimStatistics = new SimStatistics(aPlayableGame.getPlayer().getName());
    }

    public void runSimulations()
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        System.out.printf("Running %d simulations...\n", theNumSimulations);
        for (int myTrial = 0; myTrial < theNumSimulations; myTrial++) {
            double mySimPayoff = thePlayableGame.playThroughGame();
            theSimStatistics.recordResult(mySimPayoff);
        }
        System.out.println(theSimStatistics.getSimReport());
    }

    static class SimStatistics {
        private final String thePlayerName;
        private int theNumSimulationsRun;
        private final PlayerStatistics thePlayerStatistics;

        SimStatistics(String aPlayerName) {
            thePlayerName = aPlayerName;
            theNumSimulationsRun = 0;
            thePlayerStatistics = new PlayerStatistics();
        }

        void recordResult(double aPayoff) {
            theNumSimulationsRun++;
            thePlayerStatistics.updateTotalPayoff(aPayoff);
        }

        String getSimReport() {
            double myPlayerAvg = thePlayerStatistics.getTotalPayoff() / theNumSimulationsRun;
            return String.format(
                    "======== Simulation report ========\n%d simulations\nPlayer %s avg payoff: %f\n========\n",
                    theNumSimulationsRun, thePlayerName, myPlayerAvg);
        }
    }

    static class PlayerStatistics {
        private double theTotalPayoff;

        PlayerStatistics() {
            theTotalPayoff = 0.0;
        }

        double getTotalPayoff() {
            return theTotalPayoff;
        }

        void updateTotalPayoff(double aPayoff) {
            theTotalPayoff += aPayoff;
        }
    }
}
