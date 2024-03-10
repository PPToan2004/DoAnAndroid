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

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    ArrayList<Chapter> lstChapter;
    Context context;
    ChapterCallback chapterCallback;
    public ChapterAdapter(ArrayList<Chapter> lstChapter) {
        this.lstChapter = lstChapter;
    }
    public void setChapterCallback (ChapterCallback chapterCallback)
    { this.chapterCallback = chapterCallback;}
    @NonNull
    @Override
    public ChapterAdapter.ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.chapter_layout, parent, false);
        return new ChapterViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.ChapterViewHolder holder, int position) {
        Chapter item = lstChapter.get(position);

        String name = "Chapter " + item.getStt() + ": " +item.getName();
        holder.tvChapterNameC.setText(name);

        holder.tvChapterNameC.setOnClickListener(view -> chapterCallback.ItemNameClicked(item, position));
        holder.ivEdit.setOnClickListener(view -> chapterCallback.onItemEditClicked(item, position));
        holder.ivDelete.setOnClickListener(view -> chapterCallback.onItemDeleteClicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstChapter.size();
    }
    public class ChapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvChapterNameC;
        ImageView ivEdit;
        ImageView ivDelete;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvChapterNameC = itemView.findViewById(R.id.tvChapterName);
            this.ivEdit = itemView.findViewById(R.id.ivChapterEdit);
            this.ivDelete = itemView.findViewById(R.id.ivChapterDelete);
        }
    }
    public interface ChapterCallback
    {
        void ItemNameClicked(Chapter chapter, int position);
        void onItemDeleteClicked(Chapter chapter, int position);
        void onItemEditClicked(Chapter chapter, int position);
    }
}
