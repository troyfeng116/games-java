package com.tfunk116.TwoPlayer.TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tfunk116.Game.Player.Player;
import com.tfunk116.Game.Visitors.GameStateVisitor;
import com.tfunk116.TwoPlayer.Game.TwoPlayerGameStatus;
import com.tfunk116.TwoPlayer.Game.GameState.TwoPlayerGameState;

public class TicTacToeState extends TwoPlayerGameState<TicTacToeAction> {
    private final int theBoardSize;
    private final TicTacToeCell[][] theBoard;

    public TicTacToeState(Player<TicTacToeAction, TwoPlayerGameState<TicTacToeAction>> aMaxPlayer,
            Player<TicTacToeAction, TwoPlayerGameState<TicTacToeAction>> aMinPlayer,
            boolean isMaxPlayerTurn, int aBoardSize, TicTacToeCell[][] aBoard)
            throws IllegalGameStateException {
        super(aMaxPlayer, aMinPlayer, isMaxPlayerTurn);

        if (aBoardSize <= 0 || aBoard == null || aBoard.length != aBoardSize || aBoard[0].length != aBoardSize) {
            throw new IllegalGameStateException(TicTacToeState.class);
        }

        theBoardSize = aBoardSize;
        theBoard = aBoard;
    }

    public int getBoardSize() {
        return theBoardSize;
    }

    public TicTacToeCell[][] getBoard() {
        return theBoard;
    }

    @Override
    public List<TicTacToeAction> getLegalActions() {
        if (isTerminal()) {
            return List.of();
        }

        List<TicTacToeAction> myLegalActions = new ArrayList<>();
        for (int myRow = 0; myRow < theBoardSize; myRow++) {
            for (int myCol = 0; myCol < theBoardSize; myCol++) {
                if (theBoard[myRow][myCol] == TicTacToeCell.EMPTY) {
                    myLegalActions.add(new TicTacToeAction(myRow, myCol));
                }
            }
        }

        return myLegalActions;
    }

    @Override
    public boolean isLegalAction(TicTacToeAction aAction) {
        if (isTerminal()) {
            return false;
        }

        int myRow = aAction.getRow(), myCol = aAction.getCol();

        return 0 <= myRow && myRow < theBoardSize && 0 <= myCol && myCol < theBoardSize
                && theBoard[myRow][myCol] == TicTacToeCell.EMPTY;
    }

    @Override
    public TicTacToeState getSuccessor(TicTacToeAction aAction)
            throws IllegalGameActionException, IllegalGameStateException {
        if (!isLegalAction(aAction)) {
            throw new IllegalGameActionException(TicTacToeState.class);
        }

        TicTacToeCell[][] myNewBoard = new TicTacToeCell[theBoardSize][theBoardSize];
        for (int myRow = 0; myRow < theBoardSize; myRow++) {
            for (int myCol = 0; myCol < theBoardSize; myCol++) {
                myNewBoard[myRow][myCol] = theBoard[myRow][myCol];
            }
        }

        myNewBoard[aAction.getRow()][aAction.getCol()] = isMaxPlayerTurn() ? TicTacToeCell.MAX_PLAYER
                : TicTacToeCell.MIN_PLAYER;
        return new TicTacToeState(getMaxPlayer(), getMinPlayer(), !isMaxPlayerTurn(), theBoardSize, myNewBoard);
    }

    @Override
    public boolean isTerminal() {
        return checkBoardForWinner() != TwoPlayerGameStatus.IN_PROGRESS;
    }

    @Override
    public double getMaxPlayerPayoff() throws IllegalGamePayoffException {
        if (!isTerminal()) {
            throw new IllegalGamePayoffException(TicTacToeState.class);
        }

        TwoPlayerGameStatus myStatus = checkBoardForWinner();
        return switch (myStatus) {
            case IN_PROGRESS -> throw new IllegalGamePayoffException(TicTacToeState.class);
            case TIE -> 0.0;
            case MAX_PLAYER_WIN -> 1.0;
            case MIN_PLAYER_WIN -> -1.0;
        };
    }

    @Override
    public <T> T accept(GameStateVisitor<T> aVisitor) {
        return aVisitor.visit(this);
    }

    private TwoPlayerGameStatus checkBoardForWinner() {
        List<List<TicTacToeCell>> myDimsToCheck = new ArrayList<>();
        for (var myRow : theBoard) {
            myDimsToCheck.add(Arrays.asList(myRow));
        }

        for (int myCol = 0; myCol < theBoardSize; myCol++) {
            List<TicTacToeCell> myColDim = new ArrayList<>();
            for (int myRow = 0; myRow < theBoardSize; myRow++) {
                myColDim.add(theBoard[myRow][myCol]);
            }
            myDimsToCheck.add(myColDim);
        }

        List<TicTacToeCell> myDiag1 = new ArrayList<>();
        List<TicTacToeCell> myDiag2 = new ArrayList<>();
        for (int myI = 0; myI < theBoardSize; myI++) {
            myDiag1.add(theBoard[myI][myI]);
            myDiag2.add(theBoard[myI][theBoardSize - myI - 1]);
        }
        myDimsToCheck.add(myDiag1);
        myDimsToCheck.add(myDiag2);

        TwoPlayerGameStatus myStatus = TwoPlayerGameStatus.TIE;
        for (var myDim : myDimsToCheck) {
            TwoPlayerGameStatus myDimStatus = checkDimension(myDim);
            if (myDimStatus == TwoPlayerGameStatus.MAX_PLAYER_WIN
                    || myDimStatus == TwoPlayerGameStatus.MIN_PLAYER_WIN) {
                return myDimStatus;
            }

            if (myDimStatus == TwoPlayerGameStatus.IN_PROGRESS) {
                myStatus = TwoPlayerGameStatus.IN_PROGRESS;
            }
        }

        return myStatus;
    }

    private TwoPlayerGameStatus checkDimension(List<TicTacToeCell> aDim) {
        int myMaxCt = 0, myMinCt = 0;
        for (TicTacToeCell myCell : aDim) {
            switch (myCell) {
                case EMPTY -> {
                }
                case MAX_PLAYER -> myMaxCt++;
                case MIN_PLAYER -> myMinCt++;
            }
        }

        if (myMaxCt == theBoardSize) {
            return TwoPlayerGameStatus.MAX_PLAYER_WIN;
        }
        if (myMinCt == theBoardSize) {
            return TwoPlayerGameStatus.MIN_PLAYER_WIN;
        }
        return myMaxCt + myMinCt == theBoardSize ? TwoPlayerGameStatus.TIE : TwoPlayerGameStatus.IN_PROGRESS;
    }
}
