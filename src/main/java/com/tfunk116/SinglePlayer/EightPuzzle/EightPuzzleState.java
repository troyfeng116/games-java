package com.tfunk116.SinglePlayer.EightPuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Visitors.GameStateDumpVisitor;
import com.tfunk116.Game.Visitors.GameStateVisitor;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;

public class EightPuzzleState extends SinglePlayerGameState<EightPuzzleAction> {
    private static final int BOARD_SIZE = 3;
    private static final int LEN = BOARD_SIZE * BOARD_SIZE;
    private static final int EMPTY = -1;
    private static final int[] SOLVED = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, EMPTY };

    private final int theMovesTaken;
    private final int[] theBoard;

    public static final EightPuzzleState GOAL_STATE = new EightPuzzleState(null, -1, SOLVED);

    public EightPuzzleState(Player<EightPuzzleAction, SinglePlayerGameState<EightPuzzleAction>> aPlayer) {
        super(aPlayer);

        theMovesTaken = 0;
        theBoard = new int[LEN];
        theBoard[0] = EMPTY;
        for (int myIdx = 1; myIdx < LEN; myIdx++) {
            theBoard[myIdx] = myIdx;
        }

        do {
            shuffle(theBoard);
        } while (!isSolvable(theBoard));

        System.out.println(accept(GameStateDumpVisitor.INSTANCE));
    }

    private EightPuzzleState(Player<EightPuzzleAction, SinglePlayerGameState<EightPuzzleAction>> aPlayer,
            int aMovesTaken,
            int[] aBoard) {
        super(aPlayer);

        theMovesTaken = aMovesTaken;
        theBoard = aBoard;
    }

    public int[] getBoard() {
        return theBoard;
    }

    public int getMovesTaken() {
        return theMovesTaken;
    }

    @Override
    public List<EightPuzzleAction> getLegalActions() {
        int myEmptyIdx = 0;
        while (myEmptyIdx < LEN && theBoard[myEmptyIdx] != EMPTY) {
            myEmptyIdx++;
        }
        return getNeighborIdxs(myEmptyIdx).stream().map(aNeighborIdx -> new EightPuzzleAction(aNeighborIdx)).toList();
    }

    @Override
    public boolean isLegalAction(EightPuzzleAction aAction) {
        int myIdx = aAction.getIdx();
        if (myIdx < 0 || myIdx >= LEN) {
            return false;
        }

        List<Integer> myNeighborIdxs = getNeighborIdxs(myIdx);
        return myNeighborIdxs.stream()
                .map(aNeighborIdx -> theBoard[aNeighborIdx])
                .anyMatch(aNeighbor -> aNeighbor == EMPTY);
    }

    @Override
    public SinglePlayerGameState<EightPuzzleAction> getSuccessor(EightPuzzleAction aAction)
            throws IllegalGameActionException, IllegalGameStateException {
        List<Integer> myNeighborIdxs = getNeighborIdxs(aAction.getIdx());
        for (int myNeighborIdx : myNeighborIdxs) {
            if (theBoard[myNeighborIdx] == EMPTY) {
                int[] myNewBoard = Arrays.copyOf(theBoard, LEN);
                myNewBoard[aAction.getIdx()] = theBoard[myNeighborIdx];
                myNewBoard[myNeighborIdx] = theBoard[aAction.getIdx()];
                return new EightPuzzleState(getCurrentActor(), theMovesTaken + 1, myNewBoard);
            }
        }

        throw new IllegalGameActionException(getClass());
    }

    @Override
    public boolean isTerminal() {
        return Arrays.equals(theBoard, SOLVED);
    }

    @Override
    public double getPayoff() throws IllegalGamePayoffException {
        if (!isTerminal()) {
            throw new IllegalGamePayoffException(getClass());
        }

        return theMovesTaken;
    }

    @Override
    public <T> T accept(GameStateVisitor<T> aVisitor) {
        return aVisitor.visit(this);
    }

    @Override
    public boolean equals(Object aOther) {
        if (this == aOther) {
            return true;
        }

        if (!(aOther instanceof EightPuzzleState)) {
            return false;
        }

        EightPuzzleState myOtherEightPuzzleState = (EightPuzzleState) aOther;
        return Arrays.equals(theBoard, myOtherEightPuzzleState.getBoard());
    }

    @Override
    public int hashCode() {
        return Arrays.toString(theBoard).hashCode();
    }

    private List<Integer> getNeighborIdxs(int aIdx) {
        List<Integer> myNeighborIdxs = new ArrayList<>();
        if (aIdx - BOARD_SIZE >= 0) {
            myNeighborIdxs.add(aIdx - BOARD_SIZE);
        }
        if (aIdx + BOARD_SIZE < LEN) {
            myNeighborIdxs.add(aIdx + BOARD_SIZE);
        }

        switch (aIdx % BOARD_SIZE) {
            case 0 -> myNeighborIdxs.add(aIdx + 1);
            case 1 -> {
                myNeighborIdxs.add(aIdx - 1);
                myNeighborIdxs.add(aIdx + 1);
            }
            case 2 -> myNeighborIdxs.add(aIdx - 1);
        }

        return myNeighborIdxs;
    }

    private static void shuffle(int[] aArr) {
        int myN = aArr.length;
        for (int myI = myN - 1; myI > 0; myI--) {
            int myJ = (int) Math.floor(Math.random() * myI);
            int myTmp = aArr[myI];
            aArr[myI] = aArr[myJ];
            aArr[myJ] = myTmp;
        }
    }

    private static boolean isSolvable(int[] aArr) {
        int myInversions = 0;
        for (int myI = 0; myI < aArr.length; myI++) {
            for (int myJ = myI + 1; myJ < aArr.length; myJ++) {
                if (aArr[myI] != EMPTY && aArr[myJ] != EMPTY && aArr[myI] > aArr[myJ]) {
                    myInversions++;
                }
            }
        }
        return myInversions % 2 == 0;
    }
}
