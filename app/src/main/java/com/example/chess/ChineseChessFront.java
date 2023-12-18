package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chess.adapter.PieceAdapter;
import com.example.chess.iface.ChessPieceType;
import com.example.chess.models.ChineseChess;
import com.example.chess.models.ChineseChessPiece;
import com.example.chess.models.Counter;


public class ChineseChessFront extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ChineseChess game;
    private ChineseChessPiece[][] chessboard;
    private PieceAdapter adapter;
    private ImageView currentSelectedPiece = null;
    private Handler handler;
    private Counter counter;
    private Thread clockThread;
    private Runnable runnable;
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
        //start the counter
        //Warn: Controls in a UI thread cannot be modified by a non-UI thread
        //we have to use handler
        handler = new Handler(Looper.getMainLooper());
        counter = new Counter();
        clockThread = new Thread(counter);
        clockThread.start();
        runnable = new Runnable(){
            @Override
            public void run(){
                String text = counter.getTimeString();
                TextView clock = findViewById(R.id.counter);
                clock.setText(text);
                handler.postDelayed(this, 1000);
                Log.e("ChineseChess",text);
            }
        };
        handler.post(runnable);
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
                switchSide();
            }
        }
        //Log.e("ChineseChess" , "click board");
        if(!game.blackDiedPieces.empty()){
            if(game.blackDiedPieces.pop().type == ChessPieceType.GENERAL){
                Log.i("ChineseChess" , "Red Win");
                Toast.makeText(this,"Red Win",Toast.LENGTH_SHORT).show();
            }
        }
        if(!game.redDiedPieces.empty()){
            if(game.redDiedPieces.pop().type == ChessPieceType.GENERAL){
                Log.i("ChineseChess" , "Black Win");
                Toast.makeText(this,"Black Win",Toast.LENGTH_SHORT).show();
            }
        }
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
    private void switchSide(){
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