package com.tfunk116.TwoPlayer.Game.GameState;

import com.tfunk116.TwoPlayer.Game.Visitors.TwoPlayerGameStateVisitor;

interface TwoPlayerGameStateVisitable {
    <T> T accept(TwoPlayerGameStateVisitor<T> aVisitor);
}