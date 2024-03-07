package com.example.doanandroid.DB.Truyen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.doanandroid.R;
import com.example.doanandroid.utils;

import java.util.ArrayList;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.TruyenViewHolder> {

    ArrayList<Truyen> lstUser;
    Context context;
    UserCallback userCallback;
    public TruyenAdapter(ArrayList<Truyen> lstUser) {
        this.lstUser = lstUser;
    }
    public void setUserCallback (UserCallback userCallback)
    { this.userCallback = userCallback;}
    @NonNull
    @Override
    public TruyenAdapter.TruyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.truyen_layout, parent, false);
        return new TruyenViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenAdapter.TruyenViewHolder holder, int position) {
        Truyen item = lstUser.get(position);
        holder.imAvatar.setImageBitmap(utils.convertToBitMapFromAssets(context, item.getAvatar()));
        holder.tvName.setText(item.getName());
        holder.tvTypeName.setText(item.theloai_Name);
        holder.tvName.setOnClickListener(view -> userCallback.ItemNameClicked(item, position));
        holder.ivEdit.setOnClickListener(view -> userCallback.onItemEditClicked(item, position));
        holder.ivDelete.setOnClickListener(view -> userCallback.onItemDeleteClicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }
    public class TruyenViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imAvatar;
        TextView tvName;
        TextView tvTypeName;
        ImageView ivEdit;
        ImageView ivDelete;
        public TruyenViewHolder(@NonNull View itemView) {
            super(itemView);
            imAvatar = itemView.findViewById(R.id.ivAvatar);
            tvName = itemView.findViewById(R.id.tvUserName);
            tvTypeName = itemView.findViewById(R.id.tvTypeName);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
    public interface UserCallback
    {
        void ItemNameClicked(Truyen user, int position);
        void onItemDeleteClicked(Truyen user, int position);
        void onItemEditClicked(Truyen user, int position);
    }
}
