package com.example.chess.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chess.R;
import com.example.chess.iface.ChessPieceType;
import com.example.chess.iface.iPiece;
import com.example.chess.models.ChineseChessPiece;

public class PieceAdapter extends BaseAdapter {
    //pieces in grid layout
    private iPiece[] pieceList;
    private Context context;
    public PieceAdapter(Context context , iPiece[] pieceList){
        this.context = context;
        this.pieceList = pieceList;
    }
    public void updateData(iPiece[] pieces){
        this.pieceList = pieces;
    }

    @Override
    public int getCount() {
        return pieceList.length;
    }

    @Override
    public Object getItem(int i) {
        return pieceList[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.chess_piece_item,null);
        //need to edit the piece visibility
        ImageView piece = v.findViewById(R.id.chess_piece);
        //need to use different grid image
        ImageView grid = v.findViewById(R.id.grid);
        //need to change the text color
        TextView text = v.findViewById(R.id.piece_text);
        //grid.setClickable(true);
        if(pieceList[i] == null){
            piece.setVisibility(View.INVISIBLE);
            text.setVisibility(View.INVISIBLE);
            //grid.setClickable(true);
            return v;
        }
        //black and red side will have different text color
        else if (pieceList[i].getSide() == ChineseChessPiece.BLACK_SIDE) {
            text.setTextColor(Color.BLACK);
        } else if (pieceList[i].getSide() == ChineseChessPiece.RED_SIDE) {
            text.setTextColor(Color.RED);
        }
        //all type of chess piece are store in one ENUM class
        //different type will result in different appearance
        switch(pieceList[i].getType()){
            case HORSE:
                text.setText(R.string.horse);
                break;
            case CAR:
                text.setText(R.string.car);
                break;
            case MINISTER:
                if(pieceList[i].getSide() == ChineseChessPiece.BLACK_SIDE){
                    text.setText(R.string.minister_black);
                }else{
                    text.setText(R.string.minister_red);
                }
                break;
            case SCHOLAR:
                if(pieceList[i].getSide() == ChineseChessPiece.BLACK_SIDE){
                    text.setText(R.string.scholar_black);
                }else{
                    text.setText(R.string.scholar_red);
                }
                break;
            case GENERAL:
                if(pieceList[i].getSide() == ChineseChessPiece.BLACK_SIDE){
                    text.setText(R.string.general_black);
                }else{
                    text.setText(R.string.general_red);
                }
                break;
            case GUN:
                text.setText(R.string.gun);
                break;
            case SOLDIER:
                if(pieceList[i].getSide() == ChineseChessPiece.BLACK_SIDE){
                    text.setText(R.string.soldier_black);
                }else{
                    text.setText(R.string.soldier_red);
                }
                break;
        }
        return v;
    }
}
