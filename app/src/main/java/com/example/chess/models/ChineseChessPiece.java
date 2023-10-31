package com.example.chess.models;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.chess.iface.ChineseChessPieceType;

public class ChineseChessPiece extends View{
    public String name;
    public ChineseChessPieceType type;
    public static final int BLACK_SIDE = 1;
    public static final int RED_SIDE = 0;
    public int side;
    public float x = 0;
    public float y = 0;
    public ChineseChessPiece(ChineseChessPieceType type , int side , Context context){
        super(context);
        this.type = type;
        this.name = type.toString();
        this.side = side;
    }
    public void setPosition(int x,int y){
        this.x = x;
        this.y =y;
    }
    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        super.draw(canvas);
        canvas.drawCircle(x,y,10,paint);
    }
}

