package com.example.chess.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.chess.R;
import com.example.chess.iface.iPiece;

public class PieceAdapter extends BaseAdapter {
    //pieces in grid layout
    private iPiece[] pieceList;
    private Context context;
    public PieceAdapter(Context context , iPiece[] pieceList){
        this.context = context;
        this.pieceList = pieceList;
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
        grid.setClickable(false);
        if(pieceList[i] == null){
            piece.setVisibility(View.INVISIBLE);
            grid.setClickable(true);
        }
        return v;
    }
}
