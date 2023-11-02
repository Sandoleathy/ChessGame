package com.example.chess.iface;

public interface iPiece {
    String getName();
    float getX();
    float getY();
    int getSide();
    ChineseChessPieceType getType();
    void setPosition(int x, int y);
}
