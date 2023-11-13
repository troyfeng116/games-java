package com.tfunk116.Game.Simulator;

public class PlayerSimStatistics {
    private final String thePlayerName;
    private final StatsTracker theStatsTracker;

    public PlayerSimStatistics(String aPlayerName) {
        thePlayerName = aPlayerName;
        theStatsTracker = new StatsTracker();
    }

    public String getSimReport() {
        return String.format(
                "======== Simulation report: player %s ========\n%d simulations\navg payoff: %f\nmin/max: %f / %f\nstdev: %f\n========\n",
                getPlayerName(), theStatsTracker.getNumTrials(), theStatsTracker.getMean(),
                theStatsTracker.getMin(), theStatsTracker.getMax(), theStatsTracker.getStdev());
    }

    public final String getPlayerName() {
        return thePlayerName;
    }

    public final void updateTotalPayoff(double aPayoff) {
        theStatsTracker.recordValue(aPayoff);
    }
}
