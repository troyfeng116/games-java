package com.tfunk116.SingleStochastic.Java2048;

import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Heuristic.Heuristic;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.SingleStochastic.Game.GameState.SingleStochasticGameState;
import com.tfunk116.SingleStochastic.Game.Player.SingleStochasticPlayerImpl;
import com.tfunk116.SingleStochastic.Game.Policy.ExpectimaxPolicy;
import com.tfunk116.SingleStochastic.Game.Policy.SingleStochasticInputPolicy;
import com.tfunk116.SingleStochastic.Game.Policy.SingleStochasticPolicy;
import com.tfunk116.SingleStochastic.Game.Policy.SingleStochasticRandomPolicy;
import com.tfunk116.SingleStochastic.Game.Simulator.SingleStochasticGameSimulator;
import com.tfunk116.SingleStochastic.Java2048.Heuristics.Java2048CornerHeuristic;
import com.tfunk116.SingleStochastic.Java2048.Heuristics.Java2048WeightHeuristic;

public class Java2048Main {
    public static void main(String[] args)
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        // Heuristic myHeuristic = new Java2048CornerHeuristic();
        Heuristic myHeuristic = new Java2048WeightHeuristic();
        SingleStochasticPolicy<Java2048Action> myExpectimaxPolicy = new ExpectimaxPolicy<Java2048Action>(
                myHeuristic, 3);
        SingleStochasticPlayerImpl<Java2048Action> myExpectimaxPlayer = new SingleStochasticPlayerImpl<>(
                "troy",
                myExpectimaxPolicy);

        SingleStochasticPolicy<Java2048Action> myRandomPolicy = new SingleStochasticRandomPolicy<>();
        Player<Java2048Action, SingleStochasticGameState<Java2048Action>> myRandomPlayer = new SingleStochasticPlayerImpl<>(
                "troy",
                myRandomPolicy);

        SingleStochasticPolicy<Java2048Action> myInputPolicy = new SingleStochasticInputPolicy<>(
                Java2048ActionReader.INSTANCE);
        Player<Java2048Action, SingleStochasticGameState<Java2048Action>> myInputPlayer = new SingleStochasticPlayerImpl<>(
                "troy",
                myInputPolicy);

        PlayableJava2048 myPlayableJava2048 = new PlayableJava2048(myExpectimaxPlayer);
        SingleStochasticGameSimulator<Java2048Action> mySimulator = new SingleStochasticGameSimulator<>(
                3,
                myPlayableJava2048);
        mySimulator.runSimulations();
    }
}
