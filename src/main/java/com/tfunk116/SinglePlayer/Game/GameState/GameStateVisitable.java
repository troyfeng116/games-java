package com.tfunk116.SinglePlayer.Game.GameState;

import com.tfunk116.SinglePlayer.Game.Visitors.GameStateVisitor;

interface GameStateVisitable {
    <T> T accept(GameStateVisitor<T> aVisitor);
}