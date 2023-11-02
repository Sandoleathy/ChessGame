package com.example.chess.models;

import com.example.chess.iface.ChineseChessPieceType;

public class ChessFactory {
    public ChessFactory(){

    }
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
            soldiers[i] = new ChineseChessPiece(ChineseChessPieceType.SOLDIER,ChineseChessPiece.BLACK_SIDE);
        }
        for(int i = 0; i < guns.length ; i++){
            guns[i] = new ChineseChessPiece(ChineseChessPieceType.GUN,ChineseChessPiece.BLACK_SIDE);
        }
        for(int i = 0; i < cars.length ; i++){
            cars[i] = new ChineseChessPiece(ChineseChessPieceType.CAR,ChineseChessPiece.BLACK_SIDE);
        }
        for(int i = 0; i < horses.length ; i++){
            horses[i] = new ChineseChessPiece(ChineseChessPieceType.HORSE,ChineseChessPiece.BLACK_SIDE);
        }
        for(int i = 0; i < ministers.length ; i++){
            ministers[i] = new ChineseChessPiece(ChineseChessPieceType.MINISTER,ChineseChessPiece.BLACK_SIDE);
        }
        for(int i = 0; i < scholars.length ; i++){
            scholars[i] = new ChineseChessPiece(ChineseChessPieceType.SCHOLAR,ChineseChessPiece.BLACK_SIDE);
        }
        general = new ChineseChessPiece(ChineseChessPieceType.GENERAL,ChineseChessPiece.BLACK_SIDE);
        //place the chess pieces to the board
        //general
        board[0][4] = general;
        int j = 0;
        //soldier
        for(ChineseChessPiece piece : soldiers){
            board[3][j] = piece;
            j = j + 2;
        }
        //gun
        board[2][1] = guns[0];
        board[2][7] = guns[1];
        //car
        board[0][0] = cars[0];
        board[0][8] = cars[1];
        //horse
        board[0][1] = horses[0];
        board[0][7] = horses[1];
        //minister
        board[0][2] = ministers[0];
        board[0][6] = ministers[1];
        //scholar
        board[0][3] = scholars[0];
        board[0][5] = scholars[1];

        //create red side pieces
        for(int i = 0; i < soldiers.length ; i++){
            soldiers[i] = new ChineseChessPiece(ChineseChessPieceType.SOLDIER,ChineseChessPiece.RED_SIDE);
        }
        for(int i = 0; i < guns.length ; i++){
            guns[i] = new ChineseChessPiece(ChineseChessPieceType.GUN,ChineseChessPiece.RED_SIDE);
        }
        for(int i = 0; i < cars.length ; i++){
            cars[i] = new ChineseChessPiece(ChineseChessPieceType.CAR,ChineseChessPiece.RED_SIDE);
        }
        for(int i = 0; i < horses.length ; i++){
            horses[i] = new ChineseChessPiece(ChineseChessPieceType.HORSE,ChineseChessPiece.RED_SIDE);
        }
        for(int i = 0; i < ministers.length ; i++){
            ministers[i] = new ChineseChessPiece(ChineseChessPieceType.MINISTER,ChineseChessPiece.RED_SIDE);
        }
        for(int i = 0; i < scholars.length ; i++){
            scholars[i] = new ChineseChessPiece(ChineseChessPieceType.SCHOLAR,ChineseChessPiece.RED_SIDE);
        }
        general = new ChineseChessPiece(ChineseChessPieceType.GENERAL,ChineseChessPiece.RED_SIDE);

        //general
        board[9][4] = general;
        j = 0;
        //soldier
        for(ChineseChessPiece piece : soldiers){
            board[6][j] = piece;
            j = j + 2;
        }
        //gun
        board[7][1] = guns[0];
        board[7][7] = guns[0];
        //car
        board[9][0] = cars[0];
        board[9][8] = cars[1];
        //horse
        board[9][1] = horses[0];
        board[9][7] = horses[1];
        //minister
        board[9][2] = ministers[0];
        board[9][6] = ministers[1];
        //scholar
        board[9][3] = scholars[0];
        board[9][5] = scholars[1];


        //Log.w("ChineseChess" , Arrays.toString(board[0]));
        return board;
    }
}
