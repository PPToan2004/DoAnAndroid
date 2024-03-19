package com.example.doanandroid.DB.Chapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.DB.DBHelper;
import com.example.doanandroid.DB.Theloai.TheLoai;
import com.example.doanandroid.DB.Theloai.TheloaiDataQuery;
import com.example.doanandroid.DB.Truyen.Truyen;
import com.example.doanandroid.DB.Truyen.TruyenDataQuery;
import com.example.doanandroid.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadChapterActivity extends AppCompatActivity {
TextView tvTruyenName, tvChapterNumber, tvContent, tvChapterNameC;
Context context;
Button btnPreChapterC, btnNextChapterC;
Chapter chapter;
Spinner snPart;
ArrayAdapter<Chapter> truyenArrayAdapter;
ImageButton imbBackC;
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

        Truyen truyen = TruyenDataQuery.getTruyen(context,chapter.id_truyen);
        snPart = findViewById(R.id.readChapterSpinner);
        ArrayList<Chapter> lstChapter = ChapterDataQuery.getAll(this, truyen.getId());
        truyenArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstChapter);
        truyenArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snPart.setAdapter(truyenArrayAdapter);
        snPart.setSelection(chapter.getStt() - 1);
        snPart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chapter = (Chapter) snPart.getSelectedItem();
                setChapter(chapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               return;
            }
        });
        setChapter(chapter);
        btnPreChapterC.setOnClickListener(view -> ReadPreChapter());
        btnNextChapterC.setOnClickListener(view -> ReadNextChapter());

        imbBackC = findViewById(R.id.imbBackR);
        imbBackC.setOnClickListener(v->finish());
    }

    void readChapterOnSpinner()
    {
        chapter = (Chapter) snPart.getSelectedItem();
        setChapter(chapter);
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
            snPart.setSelection(stt - 1);

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
            snPart.setSelection(stt - 1);

        }
        catch (Exception e)
        {
            Toast.makeText(ReadChapterActivity.this, "Không còn chapter", Toast.LENGTH_LONG).show();
        }
    }
}