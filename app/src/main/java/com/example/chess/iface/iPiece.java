package com.example.chess.iface;

public interface iPiece {
    String getName();
    int getSide();
    ChineseChessPieceType getType();
    void setPosition(int x, int y);
}
