package com.tfunk116.SinglePlayer.Game;

import com.tfunk116.SinglePlayer.Game.Action.Action;
import com.tfunk116.SinglePlayer.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.SinglePlayer.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.SinglePlayer.Game.GameState.GameState.IllegalGameStateException;

public class GameSimulator<A extends Action> {
    private final int theNumSimulations;
    private final PlayableGame<A> thePlayableGame;
    private final SimStatistics theSimStatistics;

    public GameSimulator(int aNumSimulations, PlayableGame<A> aPlayableGame) {
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
            thePlayerStatistics.updatePayoff(aPayoff);
        }

        String getSimReport() {
            double myMaxPlayerAvg = thePlayerStatistics.getPoints() / theNumSimulationsRun;
            double myMinPlayerAvg = theMinPlayerStatistics.getPoints() / theNumSimulationsRun;
            return String.format(
                    "======== Simulation report ========\n%d simulations\nMax player %s avg wins: %f\nMin player %s avg wins: %f\n========\n",
                    theNumSimulationsRun, thePlayerName, myMaxPlayerAvg, theMinPlayerName, myMinPlayerAvg);
        }
    }

    static class PlayerStatistics {
        private double thePayoff;

        PlayerStatistics() {
            thePayoff = 0.0;
        }

        double getPoints() {
            return thePayoff;
        }

        void updatePayoff(double aPayoff) {
            thePayoff += aPayoff;
        }
    }
}
