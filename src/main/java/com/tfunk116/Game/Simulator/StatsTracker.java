package com.tfunk116.Game.Simulator;

import java.util.ArrayList;
import java.util.List;

class StatsTracker {
    private int theNumTrials;
    private List<Double> theValues;
    private double theTotal;
    private double theMin;
    private double theMax;

    public StatsTracker() {
        theNumTrials = 0;
        theValues = new ArrayList<>();
        theTotal = 0.0;
        theMin = Double.POSITIVE_INFINITY;
        theMax = Double.NEGATIVE_INFINITY;
    }

    public int getNumTrials() {
        return theNumTrials;
    }

    public double getMean() {
        return theTotal / theNumTrials;
    }

    public double getVariance() {
        double myStdev = 0.0;
        double myMean = getMean();
        for (double myVal : theValues) {
            double myDMean = myVal - myMean;
            myStdev += myDMean * myDMean;
        }
        return myStdev / theNumTrials;
    }

    public double getStdev() {
        return Math.sqrt(getVariance());
    }

    public double getMin() {
        return theMin;
    }

    public double getMax() {
        return theMax;
    }

    public void recordValue(double aValue) {
        theNumTrials++;
        theValues.add(aValue);
        theTotal += aValue;
        theMax = Math.max(theMax, aValue);
        theMin = Math.min(theMin, aValue);
    }
}