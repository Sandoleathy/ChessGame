package com.example.chess.models;
import android.util.Log;
import android.widget.ImageView;

public class ChineseChess{
    int screenHeight;
    int screenWidth;
    //this class is the main chinese chess game class
    //extends View make this class become a View custom component
    private ChineseChessPiece[][] chessboard; //the board //[row][column]
    private ImageView[][] background;
    public ChineseChess(){
        ChessFactory factory = new ChessFactory();
        chessboard = factory.initChineseChess();
        //background;
        Log.i("ChineseChess" , "board init complete");
    }

    public ChineseChessPiece[][] getChessboard(){
        return chessboard;
    }
}
