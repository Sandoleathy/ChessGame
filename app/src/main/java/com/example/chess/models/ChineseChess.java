package com.example.chess.models;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class ChineseChess extends View{
    //this class is the main chinese chess game class
    //extends View make this class become a View custom component
    private ChineseChessPiece[][] chessboard = new ChineseChessPiece[10][9]; //the board //[row][column]
    public ChineseChess(Context context){
        super(context);
        ChessFactory factory = new ChessFactory(context);
        chessboard = factory.initChineseChess();
    }
    @Override
    protected  void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for (ChineseChessPiece[] chessPieces : chessboard){
            for(ChineseChessPiece piece : chessPieces){
                if(piece != null){
                    piece.draw(canvas);
                }
            }
        }
    }
    public ChineseChessPiece[][] getChessboard(){
        return chessboard;
    }
}
