package com.example.sonlazada;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import unity.nguoi;

public class nguoiadapter extends RecyclerView.Adapter<nguoiadapter.Viewholder>{
    ArrayList<nguoi> arrayListnguoi;
    Context context;

    public nguoiadapter(ArrayList<nguoi> arrayListnguoi, Context context) {
        this.arrayListnguoi = arrayListnguoi;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.item,viewGroup,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
         viewholder.txtten.setText(arrayListnguoi.get(i).getHoten());
         Picasso.get().load(arrayListnguoi.get(i).getAnh()).into(viewholder.imganh);
    }

    @Override
    public int getItemCount() {
        return arrayListnguoi.size();
    }
    // tạo class view hoder dat ten anh xa
    public class Viewholder extends RecyclerView.ViewHolder{
        TextView txtten;
        ImageView imganh;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtten = (TextView) itemView.findViewById(R.id.textviewtenitem);
            imganh = (ImageView) itemView.findViewById(R.id.imageviewitem);
        }
    }
}
