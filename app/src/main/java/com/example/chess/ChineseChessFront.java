package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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
        if(chessboard[row][column] != null && this.game.selectedPiece == null && chessboard[row][column].side == this.game.getCurrentPlayerSide()){
            game.selectPiece(chessboard[row][column]);
            //I don't know why, but gun(7,7)'s position is wrong
            //so I add these code to ensure the position inside piece is right
            chessboard[row][column].x = row;
            chessboard[row][column].y = column;
            //-----------------------------------------
            ImageView pieceView = view.findViewById(R.id.chess_piece);
            currentSelectedPiece = pieceView;
            //ImageView pieceView = (ImageView) view;
            scaleSelectedPiece(currentSelectedPiece);
            Log.i("ChineseChess" , "click chess piece" + row + " " + column);
        }
        //if already select a piece
        else if (game.selectedPiece != null) {
            //should reset the size of current selectedPiece and change the new selected piece size
            //only click chess in the same side can change the select piece
            //change selected piece
            if(chessboard[row][column] != game.selectedPiece && chessboard[row][column] != null && chessboard[row][column].side == game.selectedPiece.side){
                resetScaleForPiece(currentSelectedPiece);
                //change the current piece
                game.selectedPiece = chessboard[row][column];
                currentSelectedPiece = view.findViewById(R.id.chess_piece);
                //scale the current piece
                scaleSelectedPiece(currentSelectedPiece);
            }
            //click selected piece
            //unselect piece
            else if(chessboard[row][column] == game.selectedPiece){
                //click itself
                resetScaleForPiece(currentSelectedPiece);
                //reset the selected piece
                currentSelectedPiece = null;
                game.selectedPiece = null;
            }
            //eat or move piece
            else if (chessboard[row][column] == null || chessboard[row][column].side != game.selectedPiece.side) {
                //piece move or eat enemy
                if(!game.movePiece(game.selectedPiece,row,column)){
                    return;
                }
                chessboard = game.getChessboard();
                adapter.updateData(convertBoard());
                //update the data
                adapter.notifyDataSetChanged();
                //reset select piece ------ go to opponent
                game.selectedPiece = null;
                currentSelectedPiece = null;
                switchSide(view);
            }
        }
        //Log.e("ChineseChess" , "click board");
    }
    private void scaleSelectedPiece(ImageView piece){
        piece.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        piece.setScaleX(1.1f);
        piece.setScaleY(1.1f);
        //request update the view
        piece.requestLayout();
    }
    private void resetScaleForPiece(ImageView piece){
        piece.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        piece.setScaleX(1.0f);
        piece.setScaleY(1.0f);
        piece.requestLayout();
    }
    private void switchSide(View view){
        this.game.switchSide();
        TextView info = findViewById(R.id.information_bar);
        if(this.game.currentPlayerSide == ChineseChessPiece.RED_SIDE){
            info.setText(R.string.red_side_info);
            info.setTextColor(ContextCompat.getColor(this,R.color.red));
        }else{
            info.setText(R.string.black_side_info);
            info.setTextColor(ContextCompat.getColor(this,R.color.black));
        }
    }
}