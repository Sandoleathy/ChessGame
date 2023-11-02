package com.example.chess.models;

import com.example.chess.iface.ChessPieceType;
import com.example.chess.iface.iPiece;

public class ChineseChessPiece implements iPiece {
    public String name;
    public ChessPieceType type;
    public static final int BLACK_SIDE = 1;
    public static final int RED_SIDE = 0;
    public int side;
    public int x = 0;
    public int y = 0;
    public ChineseChessPiece(ChessPieceType type , int side){
        this.type = type;
        this.name = type.toString();
        this.side = side;
    }
    @Override
    public void setPosition(int x,int y){
        this.x = x;
        this.y =y;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSide() {
        return side;
    }

    @Override
    public ChessPieceType getType() {
        return type;
    }


}

