package com.tfunk116.Game.Action;

import java.util.Scanner;

import com.tfunk116.Game.GameState.GameState;

public interface ActionReader<A extends Action, G extends GameState<A, G>> {
    public static final Scanner IN_SCANNER = new Scanner(System.in);

    A readAction(G aState);
}
