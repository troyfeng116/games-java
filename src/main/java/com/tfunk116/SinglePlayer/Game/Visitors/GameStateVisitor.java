package com.tfunk116.SinglePlayer.Game.Visitors;

import com.tfunk116.SinglePlayer.Java2048.Java2048State;

public interface GameStateVisitor<T> {
    public T visit(Java2048State aState);
}
