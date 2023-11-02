package com.example.chess.models;

import com.example.chess.iface.ChineseChessPieceType;
import com.example.chess.iface.iPiece;

public class ChineseChessPiece implements iPiece {
    public String name;
    public ChineseChessPieceType type;
    public static final int BLACK_SIDE = 1;
    public static final int RED_SIDE = 0;
    public int side;
    public float x = 0;
    public float y = 0;
    public ChineseChessPiece(ChineseChessPieceType type , int side){
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

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    @Override
    public int getSide() {
        return side;
    }

    @Override
    public ChineseChessPieceType getType() {
        return type;
    }


}

