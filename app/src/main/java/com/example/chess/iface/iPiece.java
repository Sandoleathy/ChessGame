package com.example.chess.iface;

public interface iPiece {
    String getName();
    int getSide();
    ChessPieceType getType();
    void setPosition(int x, int y);
}
