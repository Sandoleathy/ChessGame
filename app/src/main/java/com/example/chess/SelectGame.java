package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SelectGame extends AppCompatActivity {
    private SelectGame m = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

        Button toChineseChess = findViewById(R.id.to_chinese_chess);
        toChineseChess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ChineseChess","user play chinese chess");
                Intent chineseChess = new Intent(m,ChineseChessFront.class);
                startActivity(chineseChess);
            }
        });
        Button toTutorial = findViewById(R.id.to_tutorial);
        toTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ChineseChess","user click tutorial");
                Intent tutorial = new Intent(m,Tutorial.class);
                startActivity(tutorial);
            }
        });
    }
}