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

    EditText edtName,edtNS,edtID;
    RadioGroup radiobtnGender;
    RadioButton RadiobtnNam,RadiobtnNu;
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
                if(error()){
                Intent intent = new Intent(fixTTCNActivity.this, ContactFragment.class);
                startActivity(intent);}
            }
        });
    }

    private boolean error() {
        if(edtName.getText().toString().length()==0)
        {
            Toast.makeText(fixTTCNActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
            return  false;
        }
        else if(!RadiobtnNam.isChecked()&&!RadiobtnNu.isChecked())
        {
            Toast.makeText(fixTTCNActivity.this, "Please select your gender", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return  true;

    }


    private void addControl() {
        edtName=findViewById(R.id.edtName);
        edtID=findViewById(R.id.edtID);
        radiobtnGender=findViewById(R.id.radiobtnGender);
        RadiobtnNam=findViewById(R.id.RadiobtnNam);
        RadiobtnNu=findViewById(R.id.RadiobtnNu);
        btnsaveprofile=findViewById(R.id.btnsaveprofile);
        btnBackprofile=findViewById(R.id.btnBackprofile);



    }
}