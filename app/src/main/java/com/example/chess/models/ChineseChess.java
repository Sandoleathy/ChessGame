package com.example.chess.models;

import static com.example.chess.models.ChineseChessPiece.BLACK_SIDE;
import static com.example.chess.models.ChineseChessPiece.RED_SIDE;

import android.util.Log;
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
            Log.e("ChineseChess" , "Position invalid");
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
                if(piece.side == BLACK_SIDE){
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
            this.currentPlayerSide = BLACK_SIDE;
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
        Log.e("ChineseChess",selectedPiece.x + " " + selectedPiece.y);
        LinkedList<Integer> position;
        switch (selectedPiece.type){
            case CAR:
                /**
                 * the car's movement is the simplest
                 * car go directly and eat the first piece in its column or row
                 */
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
                //loop the row that piece has
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
            case GUN:
                /**
                 * The gun can mount and eat piece that behind another piece
                 * so we need to check almost all grid in a row or column
                 * once we meet a piece the gun is mounted. we call this piece as support
                 * if the next piece we meet is enemy the gun can eat it
                 * and we stop check
                 * also the position behind the support is unmovable
                 */
                //loop the column that piece have
                //check down
                boolean isGunMounted = false;
                Log.e("ChineseChess","" + isGunMounted);
                for(int i=x;i<chessboard.length;i++){
                    if(chessboard[i][y] != selectedPiece && chessboard[i][y] == null){
                        if(isGunMounted){
                            continue;
                        }
                        position = new LinkedList<>();
                        position.add(i);
                        position.add(y);
                        movablePositions.add(position);
                    } else if (chessboard[i][y] != selectedPiece && chessboard[i][y] != null) {
                        if(isGunMounted){
                            if(chessboard[i][y].side != selectedPiece.side){
                                position = new LinkedList<>();
                                position.add(i);
                                position.add(y);
                                movablePositions.add(position);
                            }
                            break;
                        }else{
                            isGunMounted = true;
                        }
                    }
                }
                //reset the support
                isGunMounted = false;
                //check up
                for(int i=x;i>=0;i--){
                    if(chessboard[i][y] != selectedPiece && chessboard[i][y] == null){
                        if(isGunMounted){
                            continue;
                        }
                        position = new LinkedList<>();
                        position.add(i);
                        position.add(y);
                        movablePositions.add(position);
                    } else if (chessboard[i][y] != selectedPiece && chessboard[i][y] != null) {
                        if(isGunMounted){
                            if(chessboard[i][y].side != selectedPiece.side){
                                position = new LinkedList<>();
                                position.add(i);
                                position.add(y);
                                movablePositions.add(position);
                            }
                            break;
                        }else{
                            isGunMounted = true;
                        }
                    }
                }
                //loop the row that piece has
                //reset the support
                isGunMounted = false;
                //check left
                for(int i=y;i>=0;i--){
                    if(chessboard[x][i] != selectedPiece && chessboard[x][i] == null){
                        if(isGunMounted){
                            continue;
                        }
                        position = new LinkedList<>();
                        position.add(x);
                        position.add(i);
                        movablePositions.add(position);
                    } else if (chessboard[x][i] != selectedPiece && chessboard[x][i] != null) {
                        if(isGunMounted){
                            if(chessboard[x][i].side != selectedPiece.side){
                                position = new LinkedList<>();
                                position.add(x);
                                position.add(i);
                                movablePositions.add(position);
                            }
                            break;
                        }else{
                            isGunMounted = true;
                        }
                    }
                }
                //reset the support
                isGunMounted = false;
                //check right
                for(int i=y;i<chessboard[x].length;i++) {
                    if (chessboard[x][i] != selectedPiece && chessboard[x][i] == null) {
                        if (isGunMounted) {
                            continue;
                        }
                        position = new LinkedList<>();
                        position.add(x);
                        position.add(i);
                        movablePositions.add(position);
                    } else if (chessboard[x][i] != selectedPiece && chessboard[x][i] != null) {
                        if (isGunMounted) {
                            if (chessboard[x][i].side != selectedPiece.side) {
                                position = new LinkedList<>();
                                position.add(x);
                                position.add(i);
                                movablePositions.add(position);
                            }
                            break;
                        } else {
                            isGunMounted = true;
                        }
                    }
                }
            case SOLDIER:
                /**
                 * the soldier's movement is easy but it's also hard
                 * soldiers can only move forward for 1 grid.
                 * But after they cross the river they can move to left or right for 1 grid
                 * so it's different for two sides soldiers
                  */
                switch(selectedPiece.side){
                    case RED_SIDE:
                        //check forward
                        if(x > 0){
                            if(chessboard[x-1][y] == null){
                                position = new LinkedList<>();
                                position.add(x-1);
                                position.add(y);
                                movablePositions.add(position);
                            } else if (chessboard[x-1][y] != null) {
                                if(chessboard[x-1][y].side != selectedPiece.side){
                                    position = new LinkedList<>();
                                    position.add(x-1);
                                    position.add(y);
                                    movablePositions.add(position);
                                }
                            }
                        }
                        //check left and right when solider across the river
                        if(x <= 4){
                            //check left
                            if(y>0){
                                if(chessboard[x][y-1] == null){
                                    position = new LinkedList<>();
                                    position.add(x);
                                    position.add(y-1);
                                    movablePositions.add(position);
                                }else{
                                    if(chessboard[x][y-1].side != selectedPiece.side){
                                        position = new LinkedList<>();
                                        position.add(x);
                                        position.add(y-1);
                                        movablePositions.add(position);
                                    }
                                }
                            }
                            //check right
                            if(y<8){
                                if(chessboard[x][y+1] == null){
                                    position = new LinkedList<>();
                                    position.add(x);
                                    position.add(y+1);
                                    movablePositions.add(position);
                                }else{
                                    if(chessboard[x][y+1].side != selectedPiece.side){
                                        position = new LinkedList<>();
                                        position.add(x);
                                        position.add(y+1);
                                        movablePositions.add(position);
                                    }
                                }
                            }
                        }
                        break;
                    case BLACK_SIDE:
                        //check forward(down)
                        if(x < 9){
                            if(chessboard[x+1][y] == null){
                                position = new LinkedList<>();
                                position.add(x+1);
                                position.add(y);
                                movablePositions.add(position);
                            } else if (chessboard[x+1][y] != null) {
                                if(chessboard[x+1][y].side != selectedPiece.side){
                                    position = new LinkedList<>();
                                    position.add(x+1);
                                    position.add(y);
                                    movablePositions.add(position);
                                }
                            }
                        }
                        //check left and right when solider across the river
                        if(x >= 5){
                            //check left
                            if(y>0){
                                if(chessboard[x][y-1] == null){
                                    position = new LinkedList<>();
                                    position.add(x);
                                    position.add(y-1);
                                    movablePositions.add(position);
                                }else{
                                    if(chessboard[x][y-1].side != selectedPiece.side){
                                        position = new LinkedList<>();
                                        position.add(x);
                                        position.add(y-1);
                                        movablePositions.add(position);
                                    }
                                }
                            }
                            //check right
                            if(y<8){
                                if(chessboard[x][y+1] == null){
                                    position = new LinkedList<>();
                                    position.add(x);
                                    position.add(y+1);
                                    movablePositions.add(position);
                                }else{
                                    if(chessboard[x][y+1].side != selectedPiece.side){
                                        position = new LinkedList<>();
                                        position.add(x);
                                        position.add(y+1);
                                        movablePositions.add(position);
                                    }
                                }
                            }
                        }
                        break;
                }
            case HORSE:
                /**
                 * horse's movement is complex
                 * horse can move in 日 from which means it is oblique movement
                 * but reflect in code it is simple because we only need to check at most 8 positions
                 */
                
        }
        //Log.e("ChineseChess" , movablePositions.toString());
        return movablePositions;
    }
}
