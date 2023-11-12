package com.tfunk116.SinglePlayer.EightPuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Visitors.GameStateVisitor;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;

public class EightPuzzleState extends SinglePlayerGameState<EightPuzzleAction> {
    private static final int EMPTY = -1;
    private static final int BOARD_SIZE = 3;
    private static final int LEN = BOARD_SIZE * BOARD_SIZE;
    private static final int[] SOLVED = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, EMPTY };

    private final int theMovesTaken;
    private final int[] theBoard;

    public EightPuzzleState(Player<EightPuzzleAction, SinglePlayerGameState<EightPuzzleAction>> aPlayer) {
        super(aPlayer);

        theMovesTaken = 0;
        theBoard = new int[LEN];
        theBoard[0] = -1;
        for (int myIdx = 1; myIdx < LEN; myIdx++) {
            theBoard[myIdx] = myIdx;
        }

        shuffle(theBoard);
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
        for (int myIdx = 0; myIdx < LEN; myIdx++) {
            if (theBoard[myIdx] != SOLVED[myIdx]) {
                return false;
            }
        }
        return true;
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
}
