package com.tfunk116.Game;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;

public class GameSimulator<A extends Action> {
    private final int theNumSimulations;
    private final PlayableGame<A> thePlayableGame;
    private final SimStatistics theSimStatistics;

    public GameSimulator(int aNumSimulations, PlayableGame<A> aPlayableGame) {
        theNumSimulations = aNumSimulations;
        thePlayableGame = aPlayableGame;
        theSimStatistics = new SimStatistics(aPlayableGame.getMaxPlayer().getName(),
                aPlayableGame.getMinPlayer().getName());
    }

    public void runSimulations()
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        System.out.printf("Running %d simulations...\n", theNumSimulations);
        for (int myTrial = 0; myTrial < theNumSimulations; myTrial++) {
            double myMaxSimResult = thePlayableGame.playThroughGame();
            double myMinSimResult = 0.0 - myMaxSimResult; // TODO: move total available points into Game?
            theSimStatistics.recordResult(myMaxSimResult, myMinSimResult);
        }
        System.out.println(theSimStatistics.getSimReport());
    }

    static class SimStatistics {
        private final String theMaxPlayerName;
        private final String theMinPlayerName;

        private int theNumSimulationsRun;
        private final PlayerStatistics theMaxPlayerStatistics;
        private final PlayerStatistics theMinPlayerStatistics;

        SimStatistics(String aMaxPlayerName, String aMinPlayerName) {
            theMaxPlayerName = aMaxPlayerName;
            theMinPlayerName = aMinPlayerName;
            theNumSimulationsRun = 0;
            theMaxPlayerStatistics = new PlayerStatistics();
            theMinPlayerStatistics = new PlayerStatistics();
        }

        void recordResult(double aMaxPlayerPoints, double aMinPlayerPoints) {
            theNumSimulationsRun++;
            theMaxPlayerStatistics.updatePoints(aMaxPlayerPoints);
            theMinPlayerStatistics.updatePoints(aMinPlayerPoints);
        }

        String getSimReport() {
            double myMaxPlayerAvg = theMaxPlayerStatistics.getPoints() / theNumSimulationsRun;
            double myMinPlayerAvg = theMinPlayerStatistics.getPoints() / theNumSimulationsRun;
            return String.format(
                    "======== Simulation report ========\n%d simulations\nMax player %s avg wins: %f\nMin player %s avg wins: %f\n========\n",
                    theNumSimulationsRun, theMaxPlayerName, myMaxPlayerAvg, theMinPlayerName, myMinPlayerAvg);
        }
    }

    static class PlayerStatistics {
        private double thePoints;

        PlayerStatistics() {
            thePoints = 0.0;
        }

        double getPoints() {
            return thePoints;
        }

        void updatePoints(double aPoints) {
            thePoints += aPoints;
        }
    }
}
