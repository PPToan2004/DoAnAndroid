package com.example.doanandroid.DB.Theloai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.doanandroid.R;

import java.util.ArrayList;

public class TheloaiAdapter extends RecyclerView.Adapter<TheloaiAdapter.TheloaiViewHolder> {
    ArrayList<TheLoai> lstType;
    Context context;
    TheloaiCallback typeCallBack;
    public TheloaiAdapter(ArrayList<TheLoai> lstType) {
        this.lstType = lstType;
    }
    public void setTypeCallBack(TheloaiCallback typeCallBack)
    {
        this.typeCallBack = typeCallBack;
    }
    @NonNull
    @Override
    public TheloaiAdapter.TheloaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View typeView = inflater.inflate(R.layout.theloai_layout, parent, false);
        return new TheloaiViewHolder(typeView);
    }

    @Override
    public void onBindViewHolder(@NonNull TheloaiAdapter.TheloaiViewHolder holder, int position) {
        TheLoai item = lstType.get(position);
        if(item != null && item.getName() != null)
        {
            holder.tvName.setText(position + 1 + "-" + item.getName());
            holder.ivEdit.setOnClickListener(view -> typeCallBack.onItemEditClicked(item, position));
            holder.ivDelete.setOnClickListener(view -> typeCallBack.onItemDeleteClicked(item, position));
        }
    }

    @Override
    public int getItemCount() {
        return lstType.size();
    }

    public class TheloaiViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        ImageView ivEdit;
        ImageView ivDelete;
        public TheloaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvTheLoaiName);
            ivEdit = itemView.findViewById(R.id.ivTheLoaiEdit);
            ivDelete = itemView.findViewById(R.id.ivTheLoaiDelete);
        }
    }
    public interface TheloaiCallback{
        void onItemDeleteClicked(TheLoai type, int position);
        void onItemEditClicked(TheLoai type, int position);
    }
}
