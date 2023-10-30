package com.tfunk116.Game.GameState;

import java.util.List;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.Game.Player.Player;

public abstract class GameState<A extends Action, G extends GameState<A, G>> {
    private final Player<A, G> theCurrentActor;

    public GameState(Player<A, G> aCurrentActor) {
        theCurrentActor = aCurrentActor;
    }

    public abstract List<A> getLegalActions();

    public abstract boolean isLegalAction(A aAction);

    public abstract G getSuccessor(A aAction)
            throws IllegalGameActionException, IllegalGameStateException;

    public abstract boolean isTerminal();

    public abstract double getPayoff() throws IllegalGamePayoffException;

    public final Player<A, G> getCurrentActor() {
        return theCurrentActor;
    }

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
