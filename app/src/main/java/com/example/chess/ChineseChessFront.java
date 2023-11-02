package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.chess.adapter.PieceAdapter;
import com.example.chess.models.ChineseChess;
import com.example.chess.models.ChineseChessPiece;

public class ChineseChessFront extends AppCompatActivity {
    private ChineseChess game;
    private ChineseChessPiece[][] chessboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        game = new ChineseChess();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_chess_front);
        chessboard = game.getChessboard();

        GridView boardView = findViewById(R.id.chinese_chess_board);
        PieceAdapter adapter = new PieceAdapter(this,convertBoard());
        boardView.setAdapter(adapter);

    }
    private ChineseChessPiece[] convertBoard(){
        ChineseChessPiece[] pieces = new ChineseChessPiece[90];
        int i = 0;
        for(ChineseChessPiece[] c : chessboard){
            for(ChineseChessPiece p : c){
                pieces[i] = p;
                i++;
            }
        }
        return pieces;
    }
}