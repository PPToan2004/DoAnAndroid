package com.example.doanandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.DB.Chapter.Chapter;
import com.example.doanandroid.DB.Chapter.ChapterDataQuery;
import com.example.doanandroid.DB.Chapter.ReadChapterActivity;
import com.example.doanandroid.DB.Truyen.Truyen;
import com.example.doanandroid.DB.Truyen.TruyenDataQuery;
import com.example.doanandroid.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Read_Chapter_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Read_Chapter_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Chapter chapter;
    public Read_Chapter_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Read_Chapter_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Read_Chapter_Fragment newInstance(String param1, String param2) {
        Read_Chapter_Fragment fragment = new Read_Chapter_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Bundle bundle = this.getArguments();
        chapter = (Chapter) bundle.getSerializable("ReadChapter");
    }
    TextView tvTruyenName, tvChapterNumber, tvContent, tvChapterNameC;
    Context context;
    Button btnPreChapterC, btnNextChapterC;
    Spinner snPart;
    ArrayAdapter<Chapter> truyenArrayAdapter;
    ImageButton imbBackC;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read__chapter_, container, false);
        tvTruyenName = view.findViewById(R.id.tvTruyenNameR);
        tvChapterNumber = view.findViewById(R.id.tvChapterNumber);
        tvContent = view.findViewById(R.id.tvContent);
        btnNextChapterC = view.findViewById(R.id.btnNextChapter);
        btnPreChapterC = view.findViewById(R.id.btnPreChapter);

        context = getActivity();
        Truyen truyen = TruyenDataQuery.getTruyen(context, chapter.getId_truyen());
        snPart = view.findViewById(R.id.readChapterSpinner);
        ArrayList<Chapter> lstChapter = ChapterDataQuery.getAll(getActivity(), truyen.getId());
        truyenArrayAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, lstChapter);
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
        btnPreChapterC.setOnClickListener(v -> ReadPreChapter());
        btnNextChapterC.setOnClickListener(v -> ReadNextChapter());

        imbBackC = view.findViewById(R.id.imbBackR);

        return view;
    }
    void readChapterOnSpinner()
    {
        chapter = (Chapter) snPart.getSelectedItem();
        setChapter(chapter);
    }

    String readContent(Chapter chapter)
    {
        Truyen truyen = TruyenDataQuery.getTruyen(context,chapter.getId_truyen());
        String text = "";
        try {
            InputStream is = getActivity().getAssets().open("chapters/" + truyen.getName().toLowerCase() + "/" + chapter.getContent());
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
        Truyen truyen = TruyenDataQuery.getTruyen(context,chapter.getId_truyen());
        tvTruyenName.setText(truyen.getName());
        tvChapterNumber.setText("Chapter " + chapter.getStt());

        String text = readContent(chapter);
        tvContent.setText(text);
        tvContent.setMovementMethod(new ScrollingMovementMethod());
    }

    void ReadPreChapter()
    {
        try {
            int stt = chapter.getStt() - 1;
            Truyen truyen = TruyenDataQuery.getTruyen(getActivity(), chapter.getId_truyen());
            chapter = ChapterDataQuery.getChapter(getActivity(), truyen.getId(), stt);
            snPart.setSelection(stt - 1);

        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), "Không còn chapter", Toast.LENGTH_LONG).show();
        }
    }

    void ReadNextChapter()
    {
        try {
            int stt = chapter.getStt() + 1;
            Truyen truyen = TruyenDataQuery.getTruyen(getActivity(), chapter.getId_truyen());
            chapter = ChapterDataQuery.getChapter(getActivity(), truyen.getId(), stt);
            snPart.setSelection(stt - 1);

        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), "Không còn chapter", Toast.LENGTH_LONG).show();
        }
    }
}