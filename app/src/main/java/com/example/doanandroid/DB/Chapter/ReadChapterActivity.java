package com.example.doanandroid.DB.Chapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.doanandroid.DB.DBHelper;
import com.example.doanandroid.DB.Truyen.Truyen;
import com.example.doanandroid.DB.Truyen.TruyenDataQuery;
import com.example.doanandroid.R;

import java.io.IOException;
import java.io.InputStream;

public class ReadChapterActivity extends AppCompatActivity {
TextView tvTruyenName, tvChapterNumber, tvContent;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chapter);
        tvTruyenName = findViewById(R.id.tvTruyenNameR);
        tvChapterNumber = findViewById(R.id.tvChapterNumber);
        tvContent = findViewById(R.id.tvContent);
        Intent i = getIntent();
        Chapter chapter = (Chapter) i.getSerializableExtra("ReadChapter");
        context = this;
        Truyen truyen = TruyenDataQuery.getTruyen(context,chapter.id_truyen);
        tvTruyenName.setText(truyen.getName());
        tvChapterNumber.setText("Chapter " + chapter.stt);

        String text = readContent(chapter);
        tvContent.setText(text);
        tvContent.setMovementMethod(new ScrollingMovementMethod());
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
}