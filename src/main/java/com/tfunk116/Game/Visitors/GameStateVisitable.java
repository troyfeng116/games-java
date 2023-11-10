package com.tfunk116.Game.Visitors;

public interface GameStateVisitable {
    <T> T accept(GameStateVisitor<T> aVisitor);
}
