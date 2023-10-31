package com.tfunk116.Game.Simulator;

public class PlayerSimStatistics {
    private final String thePlayerName;
    private double theTotalPayoff;
    private int theNumSimulationsRun;

    public PlayerSimStatistics(String aPlayerName) {
        thePlayerName = aPlayerName;
        theTotalPayoff = 0.0;
        theNumSimulationsRun = 0;
    }

    public String getSimReport() {
        return String.format(
                "======== Simulation report ========\n%d simulations\nPlayer %s avg payoff: %f\n========\n",
                getNumSimulationsRun(), getPlayerName(), getAvgPayoff());
    }

    public final String getPlayerName() {
        return thePlayerName;
    }

    public final int getNumSimulationsRun() {
        return theNumSimulationsRun;
    }

    public final double getAvgPayoff() {
        return theTotalPayoff / theNumSimulationsRun;
    }

    public final void updateTotalPayoff(double aPayoff) {
        theTotalPayoff += aPayoff;
        theNumSimulationsRun++;
    }
}
