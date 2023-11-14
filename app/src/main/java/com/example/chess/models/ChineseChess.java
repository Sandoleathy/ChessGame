package com.example.chess.models;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.chess.ChineseChessFront;
import com.example.chess.R;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ChineseChess {
    public ChineseChessPiece selectedPiece = null;
    public Stack<ChineseChessPiece> blackDiedPieces;
    public Stack<ChineseChessPiece> redDiedPieces;
    private ChineseChessPiece[][] chessboard; //the board //[row][column]
    private int currentPlayerSide;
    public ChineseChess(){
        ChessFactory factory = new ChessFactory();
        chessboard = factory.initChineseChess();
        this.selectedPiece = null;
        blackDiedPieces = new Stack<>();
        redDiedPieces = new Stack<>();
        this.currentPlayerSide = ChineseChessPiece.RED_SIDE;
        Log.i("ChineseChess" , "board init complete");
    }

    public ChineseChessPiece[][] getChessboard(){
        return chessboard;
    }

    /**
     * click a piece to select it
     * parameter are the coordinates of the piece
     * use to locate the piece in the 2D-array
     * replacement: double linked list is the best way to do it will take many time to implement
     * maybe the code from Data structure II will be helpful in the future
     */
    public void selectPiece(ChineseChessPiece piece){
        if(this.selectedPiece == piece){
            this.selectedPiece = null;
        }else{
            this.selectedPiece = piece;
        }
    }

    /**
     * move the piece
     * perhaps eat enemy piece
     * @param piece the piece
     * @param targetX  the target x position of piece
     * @param targetY  the target y position of piece
     */
    public boolean movePiece(ChineseChessPiece piece , int targetX , int targetY){
        //get the piece
        boolean isMoveInvalid = false;
        List<List<Integer>> movablePositions = generateMovableArea();
        for(List<Integer> i : movablePositions){
            if(targetX == i.get(0) && targetY == i.get(1)){
                isMoveInvalid = true;
                break;
            }
        }
        if(!isMoveInvalid){
            return false;
        }

        this.chessboard[piece.x][piece.y] = null;
        //never forget to change chess position and board position
        piece.setPosition(targetX,targetY);
        if(chessboard[targetX][targetY] == null){
            this.chessboard[targetX][targetY] = piece;
        }else{
            ChineseChessPiece enemy = chessboard[targetX][targetY];
            if(piece.side != enemy.side){
                //eat enemy
                if(piece.side == ChineseChessPiece.BLACK_SIDE){
                    redDiedPieces.push(enemy);
                    chessboard[targetX][targetY] = piece;
                }
                else if(piece.side == ChineseChessPiece.RED_SIDE){
                    blackDiedPieces.push(enemy);
                    chessboard[targetX][targetY] = piece;
                }
            }else{
                Log.e("ChineseChess" , "Wrong step!!!");
                return false;
            }
        }
        return true;
    }
    public void switchSide(){
        if(currentPlayerSide == ChineseChessPiece.RED_SIDE){
            this.currentPlayerSide = ChineseChessPiece.BLACK_SIDE;
        }else{
            this.currentPlayerSide = ChineseChessPiece.RED_SIDE;
        }

    }
    public int getCurrentPlayerSide(){
        return this.currentPlayerSide;
    }
    private List<List<Integer>> generateMovableArea(){
        List<List<Integer>> movablePositions = new LinkedList<>();
        int x = selectedPiece.x;
        int y = selectedPiece.y;
        LinkedList<Integer> position;
        switch (selectedPiece.type){
            case CAR:
                //loop the column that piece have
                //check down
                for(int i=x;i<chessboard.length;i++){
                    if(chessboard[i][y] != selectedPiece && chessboard[i][y] == null){
                        position = new LinkedList<>();
                        position.add(i);
                        position.add(y);
                        movablePositions.add(position);
                    } else if (chessboard[i][y] != selectedPiece && chessboard[i][y] != null) {
                        //if this piece is enemy then car can move to there and eat it
                        if(chessboard[i][y].side != selectedPiece.side){
                            position = new LinkedList<>();
                            position.add(i);
                            position.add(y);
                            movablePositions.add(position);
                        }
                        break;
                    }
                }
                //check up
                for(int i=x;i>=0;i--){
                    if(chessboard[i][y] != selectedPiece && chessboard[i][y] == null){
                        position = new LinkedList<>();
                        position.add(i);
                        position.add(y);
                        movablePositions.add(position);
                    } else if (chessboard[i][y] != selectedPiece && chessboard[i][y] != null) {
                        //if this piece is enemy then car can move to there and eat it
                        if(chessboard[i][y].side != selectedPiece.side){
                            position = new LinkedList<>();
                            position.add(i);
                            position.add(y);
                            movablePositions.add(position);
                        }
                        break;
                    }
                }
                //check left
                for(int i=y;i>=0;i--){
                    if(chessboard[x][i] != selectedPiece && chessboard[x][i] == null){
                        position = new LinkedList<>();
                        position.add(x);
                        position.add(i);
                        movablePositions.add(position);
                    } else if (chessboard[x][i] != selectedPiece && chessboard[x][i] != null) {
                        if(chessboard[x][i].side != selectedPiece.side){
                            position = new LinkedList<>();
                            position.add(x);
                            position.add(i);
                            movablePositions.add(position);
                        }
                        break;
                    }
                }
                //check right
                for(int i=y;i<chessboard[x].length;i++){
                    if(chessboard[x][i] != selectedPiece && chessboard[x][i] == null){
                        position = new LinkedList<>();
                        position.add(x);
                        position.add(i);
                        movablePositions.add(position);
                    } else if (chessboard[x][i] != selectedPiece && chessboard[x][i] != null) {
                        if(chessboard[x][i].side != selectedPiece.side){
                            position = new LinkedList<>();
                            position.add(x);
                            position.add(i);
                            movablePositions.add(position);
                        }
                        break;
                    }
                }
                break;
        }
        return movablePositions;
    }
}
