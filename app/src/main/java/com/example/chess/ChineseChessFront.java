package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.chess.adapter.PieceAdapter;
import com.example.chess.models.ChineseChess;
import com.example.chess.models.ChineseChessPiece;

public class ChineseChessFront extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ChineseChess game;
    private ChineseChessPiece[][] chessboard;
    private PieceAdapter adapter;
    private ImageView currentSelectedPiece = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        game = new ChineseChess();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_chess_front);
        chessboard = game.getChessboard();

        GridView boardView = findViewById(R.id.chinese_chess_board);
        adapter = new PieceAdapter(this,convertBoard());
        boardView.setAdapter(adapter);
        boardView.setOnItemClickListener(this);
    }

    /**
     * convert the chess board to 1D array
     * @return converted array
     */
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //cast 1D array to 2D array
        int row = i / 9;
        int column = i % 9;
        // no select piece and click a chess piece
        if(chessboard[row][column] != null && this.game.selectedPiece == null){
            game.selectPiece(chessboard[row][column]);
            ImageView pieceView = view.findViewById(R.id.chess_piece);
            currentSelectedPiece = pieceView;
            //ImageView pieceView = (ImageView) view;
            pieceView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            pieceView.setScaleX(1.1f);
            pieceView.setScaleY(1.1f);
            //request update the view
            pieceView.requestLayout();
            Log.i("ChineseChess" , "click chess piece" + row + " " + column);
        }
        //if already select a piece
        else if (game.selectedPiece != null) {
            //should reset the size of current selectedPiece and change the new selected piece size
            if(chessboard[row][column] != game.selectedPiece && chessboard[row][column] != null){
                currentSelectedPiece.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                currentSelectedPiece.setScaleX(1.0f);
                currentSelectedPiece.setScaleY(1.0f);
                //request update the view
                currentSelectedPiece.requestLayout();
                //change the current piece
                game.selectedPiece = chessboard[row][column];
                currentSelectedPiece = view.findViewById(R.id.chess_piece);
                //scale the current piece
                currentSelectedPiece.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                currentSelectedPiece.setScaleX(1.1f);
                currentSelectedPiece.setScaleY(1.1f);
                currentSelectedPiece.requestLayout();
            }
        }
    }
}