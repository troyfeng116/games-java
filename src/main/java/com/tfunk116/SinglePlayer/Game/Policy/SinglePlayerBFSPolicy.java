package com.tfunk116.SinglePlayer.Game.Policy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.AbstractMap.SimpleEntry;

import com.tfunk116.Game.Action.Action;
import com.tfunk116.SinglePlayer.Game.GameState.SinglePlayerGameState;

public class SinglePlayerBFSPolicy<A extends Action> implements SinglePlayerPolicy<A> {
    private final SinglePlayerGameState<A> theGoalState;
    private final Map<SinglePlayerGameState<A>, A> theTable;

    public SinglePlayerBFSPolicy(SinglePlayerGameState<A> aGoalState) {
        theGoalState = aGoalState;
        theTable = new HashMap<>();
    }

    @Override
    public A selectAction(SinglePlayerGameState<A> aState) {
        if (theTable.containsKey(aState)) {
            return theTable.get(aState);
        }

        Queue<SinglePlayerGameState<A>> myQueue = new LinkedList<>();
        myQueue.offer(aState);
        // node -> pred[node], action taken to get from pred[node] to node
        Map<SinglePlayerGameState<A>, SimpleEntry<SinglePlayerGameState<A>, A>> myPredTable = new HashMap<>();
        myPredTable.put(aState, null);
        int myDist = 0;
        while (!myQueue.isEmpty()) {
            int mySz = myQueue.size();
            for (int myI = 0; myI < mySz; myI++) {
                SinglePlayerGameState<A> myCurState = myQueue.poll();
                if (myCurState.equals(theGoalState)) {
                    System.out.println(myDist);
                    A myBestAction = null;
                    SimpleEntry<SinglePlayerGameState<A>, A> myPred;
                    while ((myPred = myPredTable.get(myCurState)) != null) {
                        myCurState = myPred.getKey();
                        myBestAction = myPred.getValue();
                        theTable.put(myCurState, myBestAction);
                    }
                    return myBestAction;
                }

                List<A> myActions = myCurState.getLegalActions();
                for (A myAction : myActions) {
                    try {
                        SinglePlayerGameState<A> myNextState = myCurState.getSuccessor(myAction);
                        if (!myPredTable.containsKey(myNextState)) {
                            myQueue.offer(myNextState);
                            myPredTable.put(myNextState, new SimpleEntry<>(myCurState, myAction));
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }

            myDist++;
        }

        return null;
    }
}
