package com.tfunk116.Game.GameState;

import java.util.List;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Player.Player;

public abstract class GameState<A extends Action> implements GameStateVisitable {
    private final Player<A> theMaxPlayer;
    private final Player<A> theMinPlayer;
    private final Player<A> theCurrentActor;

    public GameState(Player<A> aMaxPlayer, Player<A> aMinPlayer, boolean isMaxPlayerTurn) {
        theMaxPlayer = aMaxPlayer;
        theMinPlayer = aMinPlayer;
        theCurrentActor = isMaxPlayerTurn ? aMaxPlayer : aMinPlayer;
    }

    public abstract List<A> getLegalActions();

    public Player<A> getMaxPlayer() {
        return theMaxPlayer;
    }

    public Player<A> getMinPlayer() {
        return theMinPlayer;
    }

    public Player<A> getCurrentActor() {
        return theCurrentActor;
    }

    public boolean isMaxPlayerTurn() {
        return getCurrentActor().equals(getMaxPlayer());
    }

    public abstract boolean isLegalAction(A aAction);

    public abstract GameState<A> getSuccessor(A aAction) throws IllegalGameActionException, IllegalGameStateException;

    public abstract boolean isTerminal();

    public abstract double getMaxPlayerPayoff() throws IllegalGamePayoffException;

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
