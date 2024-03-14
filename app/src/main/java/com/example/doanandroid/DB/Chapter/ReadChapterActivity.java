package com.example.doanandroid.DB.Chapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.DB.DBHelper;
import com.example.doanandroid.DB.Truyen.Truyen;
import com.example.doanandroid.DB.Truyen.TruyenDataQuery;
import com.example.doanandroid.R;

import java.io.IOException;
import java.io.InputStream;

public class ReadChapterActivity extends AppCompatActivity {
TextView tvTruyenName, tvChapterNumber, tvContent;
Button btnPreChapterC, btnNextChapterC;
Context context;
Chapter chapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chapter);
        tvTruyenName = findViewById(R.id.tvTruyenNameR);
        tvChapterNumber = findViewById(R.id.tvChapterNumber);
        tvContent = findViewById(R.id.tvContent);
        btnNextChapterC = findViewById(R.id.btnNextChapter);
        btnPreChapterC = findViewById(R.id.btnPreChapter);

        Intent i = getIntent();
        chapter = (Chapter) i.getSerializableExtra("ReadChapter");
        context = this;
        setChapter(chapter);
        btnPreChapterC.setOnClickListener(view -> ReadPreChapter());
        btnNextChapterC.setOnClickListener(view -> ReadNextChapter());
    }

    String readContent(Chapter chapter)
    {
        Truyen truyen = TruyenDataQuery.getTruyen(context,chapter.id_truyen);
        String text = "";
        try {
            InputStream is = getAssets().open("chapters/" + truyen.getName().toLowerCase() + "/" + chapter.getContent());
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return text;
    }

    void setChapter(Chapter chapter)
    {
        Truyen truyen = TruyenDataQuery.getTruyen(context,chapter.id_truyen);
        tvTruyenName.setText(truyen.getName());
        tvChapterNumber.setText("Chapter " + chapter.stt);

        String text = readContent(chapter);
        tvContent.setText(text);
        tvContent.setMovementMethod(new ScrollingMovementMethod());
    }

    void ReadPreChapter()
    {
        try {
            int stt = chapter.getStt() - 1;
            Truyen truyen = TruyenDataQuery.getTruyen(this, chapter.id_truyen);
            chapter = ChapterDataQuery.getChapter(this, truyen.getId(), stt);
            setChapter(chapter);
        }
        catch (Exception e)
        {
            Toast.makeText(ReadChapterActivity.this, "Không còn chapter", Toast.LENGTH_LONG).show();
        }
    }

    void ReadNextChapter()
    {
        try {
            int stt = chapter.getStt() + 1;
            Truyen truyen = TruyenDataQuery.getTruyen(this, chapter.id_truyen);
            chapter = ChapterDataQuery.getChapter(this, truyen.getId(), stt);
            setChapter(chapter);
        }
        catch (Exception e)
        {
            Toast.makeText(ReadChapterActivity.this, "Không còn chapter", Toast.LENGTH_LONG).show();
        }
    }
}