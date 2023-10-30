package com.tfunk116.SinglePlayer.Game.GameState;

import com.tfunk116.SinglePlayer.Game.Visitors.SinglePlayerGameStateVisitor;

interface SinglePlayerGameStateVisitable {
    <T> T accept(SinglePlayerGameStateVisitor<T> aVisitor);
}