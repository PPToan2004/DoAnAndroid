package com.example.doanandroid.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.doanandroid.R;

public class fixTTCNActivity extends AppCompatActivity {

    EditText edtName,edtGmail,edtMK;
    Button btnsaveprofile,btnBackprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_ttcnactivity);
        addControl();
        addEvents();
    }

    private void addEvents() {
        btnsaveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }




    private void addControl() {
        edtName=findViewById(R.id.edtName);
        edtGmail=findViewById(R.id.edtGmail);
        edtMK=findViewById(R.id.edtMK);
        btnsaveprofile=findViewById(R.id.btnsaveprofile);
        btnBackprofile=findViewById(R.id.btnBackprofile);



    }
}