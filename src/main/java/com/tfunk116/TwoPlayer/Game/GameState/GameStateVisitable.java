package com.tfunk116.TwoPlayer.Game.GameState;

import com.tfunk116.TwoPlayer.Game.Visitors.GameStateVisitor;

interface GameStateVisitable {
    <T> T accept(GameStateVisitor<T> aVisitor);
}