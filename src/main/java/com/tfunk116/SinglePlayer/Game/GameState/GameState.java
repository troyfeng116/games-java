package com.tfunk116.SinglePlayer.Game.GameState;

import java.util.List;

import com.tfunk116.SinglePlayer.Game.Action.Action;
import com.tfunk116.SinglePlayer.Game.Player.Player;

public abstract class GameState<A extends Action> implements GameStateVisitable {
    private final Player<A> thePlayer;

    public GameState(Player<A> aPlayer) {
        thePlayer = aPlayer;
    }

    public abstract List<A> getLegalActions();

    public Player<A> getCurrentActor() {
        return thePlayer;
    }

    public abstract boolean isLegalAction(A aAction);

    public abstract GameState<A> getSuccessor(A aAction) throws IllegalGameActionException, IllegalGameStateException;

    public abstract boolean isTerminal();

    public abstract double getPayoff() throws IllegalGamePayoffException;

    public static class IllegalGameStateException extends Exception {
        public IllegalGameStateException(Class<?> aClass) {
            super(String.format("[%s]\n", aClass.getName()));
        }
    }

    public static class IllegalGameActionException extends Exception {
        public IllegalGameActionException(Class<?> aClass) {
            super(String.format("[%s]\n", aClass.getName()));
        }
    }

    public static class IllegalGamePayoffException extends Exception {
        public IllegalGamePayoffException(Class<?> aClass) {
            super(String.format("[%s]\n", aClass.getName()));
        }
    }
}
