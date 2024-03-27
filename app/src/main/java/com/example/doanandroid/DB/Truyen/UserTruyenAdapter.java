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

public class UserTruyenAdapter extends RecyclerView.Adapter<UserTruyenAdapter.UserTruyenViewHolder> {
    ArrayList<Truyen> lstUser;
    Context context;
    UserTruyenAdapter.UserCallback userCallback;
    public UserTruyenAdapter(ArrayList<Truyen> lstUser) {this.lstUser = lstUser;}
    public void setUserCallback (UserTruyenAdapter.UserCallback userCallback)
    { this.userCallback = userCallback;}

    @NonNull
    @Override
    public UserTruyenAdapter.UserTruyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.user_truyen_layout, parent, false);
        return new UserTruyenAdapter.UserTruyenViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserTruyenAdapter.UserTruyenViewHolder holder, int position) {
        Truyen item = lstUser.get(position);
        holder.imAvatar.setImageBitmap(utils.convertToBitMapFromAssets(context, item.getAvatar()));
        holder.tvName.setText(item.getName());
        holder.tvTypeName.setText(item.theloai_Name);
        holder.tvName.setOnClickListener(view -> userCallback.ItemNameClicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    public class UserTruyenViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imAvatar;
        TextView tvName;
        TextView tvTypeName;

        public UserTruyenViewHolder(@NonNull View itemView) {
            super(itemView);
            imAvatar = itemView.findViewById(R.id.ivAvatar);
            tvName = itemView.findViewById(R.id.tvUserName);
            tvTypeName = itemView.findViewById(R.id.tvTypeName);
        }
    }
    public interface UserCallback
    {
        void ItemNameClicked(Truyen user, int position);
    }
}
