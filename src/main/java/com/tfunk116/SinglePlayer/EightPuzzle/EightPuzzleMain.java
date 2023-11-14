package com.tfunk116.SinglePlayer.EightPuzzle;

import com.tfunk116.Game.GameState.GameState.IllegalGameActionException;
import com.tfunk116.Game.GameState.GameState.IllegalGamePayoffException;
import com.tfunk116.Game.GameState.GameState.IllegalGameStateException;
import com.tfunk116.Game.Player.Player;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;
import com.tfunk116.SinglePlayer.Game.Playable.SinglePlayerPlayable;
import com.tfunk116.SinglePlayer.Game.Player.SinglePlayerImpl;
import com.tfunk116.SinglePlayer.Game.Policy.SinglePlayerBFSPolicy;
import com.tfunk116.SinglePlayer.Game.Policy.SinglePlayerInputPolicy;
import com.tfunk116.SinglePlayer.Game.Policy.SinglePlayerPolicy;
import com.tfunk116.SinglePlayer.Game.Policy.SinglePlayerRandomPolicy;
import com.tfunk116.SinglePlayer.Game.Simulator.SinglePlayerGameSimulator;

public class EightPuzzleMain {
    public static void main(String[] args)
            throws IllegalGameStateException, IllegalGameActionException, IllegalGamePayoffException {
        SinglePlayerPolicy<EightPuzzleAction> myInputPolicy = new SinglePlayerInputPolicy<EightPuzzleAction>(
                EightPuzzleActionReader.INSTANCE);
        Player<EightPuzzleAction, SinglePlayerGameState<EightPuzzleAction>> myInputPlayer = new SinglePlayerImpl<>(
                "troy",
                myInputPolicy);

        SinglePlayerPolicy<EightPuzzleAction> myRandomPolicy = new SinglePlayerRandomPolicy<>();
        Player<EightPuzzleAction, SinglePlayerGameState<EightPuzzleAction>> myRandomPlayer = new SinglePlayerImpl<>(
                "troy",
                myRandomPolicy);

        SinglePlayerPolicy<EightPuzzleAction> myBFSPolicy = new SinglePlayerBFSPolicy<>(
                EightPuzzleState.GOAL_STATE);
        Player<EightPuzzleAction, SinglePlayerGameState<EightPuzzleAction>> myBFSPlayer = new SinglePlayerImpl<>(
                "troy",
                myBFSPolicy);

        SinglePlayerPlayable<EightPuzzleAction> myPlayable = new PlayableEightPuzzle(myInputPlayer);
        SinglePlayerGameSimulator<EightPuzzleAction> mySimulator = new SinglePlayerGameSimulator<>(100,
                myPlayable);
        mySimulator.runSimulations();
    }
}
