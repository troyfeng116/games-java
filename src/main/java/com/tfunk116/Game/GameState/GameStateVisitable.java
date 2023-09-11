package com.tfunk116.Game.GameState;

import com.tfunk116.Game.Visitors.GameStateVisitor;

interface GameStateVisitable {
    <T> T accept(GameStateVisitor<T> aVisitor);
}