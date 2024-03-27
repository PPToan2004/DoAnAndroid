package com.example.doanandroid.DB.Chapter;

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

public class UserChapterAdapter extends RecyclerView.Adapter<UserChapterAdapter.UserChapterViewHolder> {
    ArrayList<Chapter> lstChapter;
    Context context;
    UserChapterCallback chapterCallback;
    public UserChapterAdapter(ArrayList<Chapter> lstChapter) {
        this.lstChapter = lstChapter;
    }
    public void setChapterCallback (UserChapterCallback chapterCallback)
    { this.chapterCallback = chapterCallback;}
    @NonNull
    @Override
    public UserChapterAdapter.UserChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.user_chapter_layout, parent, false);
        return new UserChapterAdapter.UserChapterViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserChapterAdapter.UserChapterViewHolder holder, int position) {
        Chapter item = lstChapter.get(position);

        String name = "Chapter " + item.getStt() + ": " +item.getName();
        holder.tvChapterNameC.setText(name);

        holder.tvChapterNameC.setOnClickListener(view -> chapterCallback.ItemNameClicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstChapter.size();
    }
    public class UserChapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvChapterNameC;
        public UserChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvChapterNameC = itemView.findViewById(R.id.tvChapterName);
        }
    }
    public interface UserChapterCallback
    {
        void ItemNameClicked(Chapter chapter, int position);
    }
}
