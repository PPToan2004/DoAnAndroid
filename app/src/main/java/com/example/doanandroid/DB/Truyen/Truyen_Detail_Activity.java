package com.example.doanandroid.DB.Truyen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.DB.Chapter.Chapter;
import com.example.doanandroid.DB.Chapter.ChapterAdapter;
import com.example.doanandroid.DB.Chapter.ChapterDataQuery;
import com.example.doanandroid.R;
import com.example.doanandroid.utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Truyen_Detail_Activity extends AppCompatActivity implements ChapterAdapter.ChapterCallback {
Context context;
    RecyclerView rcListCode;
    ArrayList<Chapter> lstChapter;
    ChapterAdapter chapterAdapter;
    FloatingActionButton fbAddChapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen_detail);
        ImageView ivAvatarC = findViewById(R.id.ivDeAvatar);
        TextView tvUserDeNameC = findViewById(R.id.tvDeName);
        TextView tvUserDeTypeC = findViewById(R.id.tvDeType);

        Intent i = getIntent();
        Truyen truyen = (Truyen) i.getSerializableExtra("TruyenDetail");
        String name = truyen.getName();
        String type = "Thể loại: " + truyen.getTheloai_Name();

        tvUserDeNameC.setText(name);
        tvUserDeTypeC.setText(type);
        context = this;
        ivAvatarC.setImageBitmap(utils.convertToBitMapFromAssets(context, truyen.getAvatar()));

        rcListCode = findViewById(R.id.rvDeChapterList);
        fbAddChapter = findViewById(R.id.fbAddChapter);
        fbAddChapter.setOnClickListener(view -> addChapterrDialog());

        lstChapter = new ArrayList<>();
        lstChapter = ChapterDataQuery.getAll(this);
        chapterAdapter = new ChapterAdapter(lstChapter);
        chapterAdapter.setChapterCallback((ChapterAdapter.ChapterCallback) this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcListCode.setAdapter(chapterAdapter);
        rcListCode.setLayoutManager(linearLayoutManager);
    }
    void addChapterrDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Truyen_Detail_Activity.this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_chapter, null);
        alertDialog.setView(dialogView);

        EditText edName = dialogView.findViewById(R.id.edName);
        EditText edContent = dialogView.findViewById(R.id.edContent);
        EditText edSTT = dialogView.findViewById(R.id.edSTT);

        Spinner snPart= dialogView.findViewById(R.id.chapterSpinner);
        ArrayList<Truyen> lstTruyen =  TruyenDataQuery.getAll(this);
        lstTruyen.add(0,new Truyen(0, "Chọn bộ truyện", null));
        ArrayAdapter<Truyen> truyenArrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstTruyen);
        truyenArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snPart.setAdapter(truyenArrayAdapter);

        alertDialog.setPositiveButton("Đồng ý", ((dialog, which) ->
        {
            String name = edName.getText().toString();
            String stt = edSTT.getText().toString();
            String content = edContent.getText().toString();
            Truyen itemTruyen = (Truyen) snPart.getSelectedItem();
            if (itemTruyen.id == 0)
            {
                Toast.makeText(Truyen_Detail_Activity.this, "Vui lòng chọn lọai hoa", Toast.LENGTH_LONG).show();
            } else
            if (name.isEmpty() || stt.isEmpty() || Integer.parseInt(stt) < 0 || content.isEmpty())
                Toast.makeText(Truyen_Detail_Activity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            else {
                Chapter chapter = new Chapter(0, name, content, Integer.parseInt(stt));
                chapter.setId_truyen(itemTruyen.id);
                long id = ChapterDataQuery.insert(Truyen_Detail_Activity.this, chapter);
                if (id > 0) {
                    Toast.makeText(Truyen_Detail_Activity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        }
        ));
        alertDialog.setNegativeButton("Hủy", ((dialog, which) ->
        {
            dialog.dismiss();
        }
        ));
        alertDialog.create();
        alertDialog.show();
    }

    void updateChapterrDialog(Chapter chapter) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Truyen_Detail_Activity.this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_add_chapter, null);
        alertDialog.setView(dialogView);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText edSTT = (EditText) dialogView.findViewById(R.id.edSTT);
        EditText edContent = (EditText) dialogView.findViewById(R.id.edContent);
        edName.setText(chapter.getName());
        edSTT.setText(String.valueOf(chapter.getStt()));
        edContent.setText(chapter.getContent());

        Spinner snPart= dialogView.findViewById(R.id.chapterSpinner);
        ArrayList<Truyen> lstTruyen =  TruyenDataQuery.getAll(this);
        lstTruyen.add(0,new Truyen(0, "Chọn bộ truyện", null));
        ArrayAdapter<Truyen> truyenArrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstTruyen);
        truyenArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snPart.setAdapter(truyenArrayAdapter);

        edName.setText(chapter.getName());
        edSTT.setText(chapter.getStt() + "");
        edContent.setText(chapter.getContent());
        snPart.setSelection(chapter.getId_truyen());

        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            chapter.setName(edName.getText().toString());
            chapter.setStt(Integer.parseInt(edSTT.getText().toString()));
            chapter.setContent(edContent.getText().toString());
            Truyen itemTruyen = (Truyen) snPart.getSelectedItem();
            if (itemTruyen.id == 0)
            {
                Toast.makeText(Truyen_Detail_Activity.this, "Vui lòng chọn bộ truyện", Toast.LENGTH_LONG).show();
            } else
            if (chapter.getName().isEmpty() || chapter.getStt() < 0 || chapter.getContent().isEmpty())
                Toast.makeText(Truyen_Detail_Activity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            else {
                int id = ChapterDataQuery.update(Truyen_Detail_Activity.this, chapter);
                if (id > 0) {
                    Toast.makeText(Truyen_Detail_Activity.this, "Cập nhật chapter thành công.", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Hủy", (dialog, which) -> {
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }

    void resetData() {
        lstChapter.clear();
        lstChapter.addAll(ChapterDataQuery.getAll(Truyen_Detail_Activity.this));
        chapterAdapter.notifyDataSetChanged();
    }

    @Override
    public void ItemNameClicked(Chapter chapter, int position) {
        return;
    }

    @Override
    public void onItemDeleteClicked(Chapter chapter, int position) {
        boolean rs = ChapterDataQuery.delete(Truyen_Detail_Activity.this, chapter.getId());
        if (rs) {
            Toast.makeText(this, "Xoá thành công", Toast.LENGTH_LONG).show();
            resetData();
        } else {
            Toast.makeText(this, "Xoá thất bại", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemEditClicked(Chapter chapter, int position) {
        updateChapterrDialog(chapter);
    }
}