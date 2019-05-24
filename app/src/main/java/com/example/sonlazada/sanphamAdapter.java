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

import java.text.DecimalFormat;
import java.util.ArrayList;

import unity.Sanpham;

public class sanphamAdapter extends RecyclerView.Adapter<sanphamAdapter.Itemholder> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

    public sanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public Itemholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dongsp_khuyenmai,null);
        Itemholder itemholder = new Itemholder(v);
        return itemholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Itemholder itemholder, int i) {
            Sanpham sanpham = arraysanpham.get(i);
            itemholder.txttensp.setText(sanpham.getTensp());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            itemholder.txtgiasp.setText("Giá: " + decimalFormat.format(sanpham.getGiasp()) + " VNĐ");
            Picasso.get().load(sanpham.getLinkanhsp()).into(itemholder.imghinhsp);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class Itemholder extends RecyclerView.ViewHolder{
        public ImageView imghinhsp;
        public TextView txttensp;
        public TextView txtgiasp;

        public Itemholder(@NonNull final View itemView) {
            super(itemView);
            imghinhsp = (ImageView) itemView.findViewById(R.id.imageviewsp);
            txttensp = (TextView) itemView.findViewById(R.id.textviewtensp);
            txtgiasp = (TextView) itemView.findViewById(R.id.textviewgiasp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }
}
