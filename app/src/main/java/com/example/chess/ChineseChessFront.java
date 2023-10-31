package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.chess.models.ChineseChess;

public class ChineseChessFront extends AppCompatActivity {
    private ChineseChess game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        game = new ChineseChess(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_chess_front);
    }
}