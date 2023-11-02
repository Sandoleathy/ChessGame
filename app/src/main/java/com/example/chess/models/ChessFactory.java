package com.example.chess.models;

import com.example.chess.iface.ChessPieceType;

public class ChessFactory {
    public ChessFactory(){

    }

    /**
     * don't care this method
     * it is a SHIT MOUNTAIN
     * because I change the data structure lots of times
     * @return
     */
    public ChineseChessPiece[][] initChineseChess(){
        //init the board
        ChineseChessPiece[][] board = new ChineseChessPiece[10][9];//[row][column]
        //init all types of pieces
        ChineseChessPiece[] soldiers = new ChineseChessPiece[5];
        ChineseChessPiece[] guns = new ChineseChessPiece[2];
        ChineseChessPiece[] cars = new ChineseChessPiece[2];
        ChineseChessPiece[] horses = new ChineseChessPiece[2];
        ChineseChessPiece[] ministers = new ChineseChessPiece[2];
        ChineseChessPiece[] scholars = new ChineseChessPiece[2];
        ChineseChessPiece general;
        //create black side pieces
        for(int i = 0; i < soldiers.length ; i++){
            soldiers[i] = new ChineseChessPiece(ChessPieceType.SOLDIER,ChineseChessPiece.BLACK_SIDE);
        }
        for(int i = 0; i < guns.length ; i++){
            guns[i] = new ChineseChessPiece(ChessPieceType.GUN,ChineseChessPiece.BLACK_SIDE);
        }
        for(int i = 0; i < cars.length ; i++){
            cars[i] = new ChineseChessPiece(ChessPieceType.CAR,ChineseChessPiece.BLACK_SIDE);
        }
        for(int i = 0; i < horses.length ; i++){
            horses[i] = new ChineseChessPiece(ChessPieceType.HORSE,ChineseChessPiece.BLACK_SIDE);
        }
        for(int i = 0; i < ministers.length ; i++){
            ministers[i] = new ChineseChessPiece(ChessPieceType.MINISTER,ChineseChessPiece.BLACK_SIDE);
        }
        for(int i = 0; i < scholars.length ; i++){
            scholars[i] = new ChineseChessPiece(ChessPieceType.SCHOLAR,ChineseChessPiece.BLACK_SIDE);
        }
        general = new ChineseChessPiece(ChessPieceType.GENERAL,ChineseChessPiece.BLACK_SIDE);
        //place the chess pieces to the board
        //general
        general.setPosition(0,4);
        board[0][4] = general;
        int j = 0;
        //soldier
        for(ChineseChessPiece piece : soldiers){
            piece.setPosition(3,j);
            board[3][j] = piece;
            j = j + 2;
        }
        //gun
        guns[0].setPosition(2,1);
        guns[1].setPosition(2,7);
        board[2][1] = guns[0];
        board[2][7] = guns[1];
        //car
        cars[0].setPosition(0,0);
        cars[1].setPosition(0,8);
        board[0][0] = cars[0];
        board[0][8] = cars[1];
        //horse
        horses[0].setPosition(0,1);
        horses[1].setPosition(0,7);
        board[0][1] = horses[0];
        board[0][7] = horses[1];
        //minister
        ministers[0].setPosition(0,2);
        ministers[1].setPosition(0,6);
        board[0][2] = ministers[0];
        board[0][6] = ministers[1];
        //scholar
        scholars[0].setPosition(0,3);
        scholars[1].setPosition(0,5);
        board[0][3] = scholars[0];
        board[0][5] = scholars[1];

        //create red side pieces
        for(int i = 0; i < soldiers.length ; i++){
            soldiers[i] = new ChineseChessPiece(ChessPieceType.SOLDIER,ChineseChessPiece.RED_SIDE);
        }
        for(int i = 0; i < guns.length ; i++){
            guns[i] = new ChineseChessPiece(ChessPieceType.GUN,ChineseChessPiece.RED_SIDE);
        }
        for(int i = 0; i < cars.length ; i++){
            cars[i] = new ChineseChessPiece(ChessPieceType.CAR,ChineseChessPiece.RED_SIDE);
        }
        for(int i = 0; i < horses.length ; i++){
            horses[i] = new ChineseChessPiece(ChessPieceType.HORSE,ChineseChessPiece.RED_SIDE);
        }
        for(int i = 0; i < ministers.length ; i++){
            ministers[i] = new ChineseChessPiece(ChessPieceType.MINISTER,ChineseChessPiece.RED_SIDE);
        }
        for(int i = 0; i < scholars.length ; i++){
            scholars[i] = new ChineseChessPiece(ChessPieceType.SCHOLAR,ChineseChessPiece.RED_SIDE);
        }
        general = new ChineseChessPiece(ChessPieceType.GENERAL,ChineseChessPiece.RED_SIDE);

        //general
        general.setPosition(9,4);
        board[9][4] = general;
        j = 0;
        //soldier
        for(ChineseChessPiece piece : soldiers){
            piece.setPosition(6,j);
            board[6][j] = piece;
            j = j + 2;
        }
        //gun
        guns[0].setPosition(7,1);
        guns[1].setPosition(7,7);
        board[7][1] = guns[0];
        board[7][7] = guns[0];
        //car
        cars[0].setPosition(9,0);
        cars[1].setPosition(9,8);
        board[9][0] = cars[0];
        board[9][8] = cars[1];
        //horse
        horses[0].setPosition(9,1);
        horses[1].setPosition(9,7);
        board[9][1] = horses[0];
        board[9][7] = horses[1];
        //minister
        ministers[0].setPosition(9,2);
        ministers[1].setPosition(9,6);
        board[9][2] = ministers[0];
        board[9][6] = ministers[1];
        //scholar
        scholars[0].setPosition(9,3);
        scholars[1].setPosition(9,5);
        board[9][3] = scholars[0];
        board[9][5] = scholars[1];

        //Log.w("ChineseChess" , Arrays.toString(board[0]));
        return board;
    }
}
