package com.tfunk116.SingleStochastic.Java2048;

import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Heuristic.Heuristic;
import com.tfunk116.SingleStochastic.Game.Player.SingleStochasticPlayerImpl;
import com.tfunk116.SingleStochastic.Game.Policy.ExpectimaxPolicy;
import com.tfunk116.SingleStochastic.Game.Policy.SingleStochasticPolicy;
import com.tfunk116.SingleStochastic.Game.Simulator.SingleStochasticGameSimulator;
import com.tfunk116.SingleStochastic.Java2048.Heuristics.Java2048CornerHeuristic;
import com.tfunk116.SingleStochastic.Java2048.Heuristics.Java2048WeightHeuristic;

public class Java2048Main {
        public static void main(String[] args)
                        throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
                // Heuristic myHeuristic = new Java2048CornerHeuristic();
                Heuristic myHeuristic = new Java2048WeightHeuristic();
                SingleStochasticPolicy<Java2048Action> myCornerExpectimaxPolicy = new ExpectimaxPolicy<Java2048Action>(
                                myHeuristic, 3);
                SingleStochasticPlayerImpl<Java2048Action> myCornerExpectimaxPlayer = new SingleStochasticPlayerImpl<>(
                                "troy",
                                myCornerExpectimaxPolicy);

                // SinglePlayerStochasticPolicy<Java2048Action> myRandomPolicy = new
                // SinglePlayerStochasticRandomPolicy<Java2048Action>();
                // SinglePlayerStochasticImpl<Java2048Action> myRandomPlayer = new
                // SinglePlayerStochasticImpl<>("troy",
                // myRandomPolicy);

                // SinglePlayerStochasticPolicy<Java2048Action> myInputPolicy = new
                // Java2048InputPolicy();
                // SinglePlayerStochasticImpl<Java2048Action> myInputPlayer = new
                // SinglePlayerStochasticImpl<>("troy",
                // myInputPolicy);

                PlayableJava2048 myPlayableJava2048 = new PlayableJava2048(myCornerExpectimaxPlayer);
                SingleStochasticGameSimulator<Java2048Action> mySimulator = new SingleStochasticGameSimulator<>(
                                3,
                                myPlayableJava2048);
                mySimulator.runSimulations();
        }
}
