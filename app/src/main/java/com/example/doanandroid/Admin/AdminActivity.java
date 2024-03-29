package com.example.doanandroid.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doanandroid.DB.Theloai.TheLoaiActivity;
import com.example.doanandroid.DB.Truyen.TruyenActivity;
import com.example.doanandroid.R;

public class AdminActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    Button btnDXadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        addControl();
        addEventsBtnExits();
    }

    private void addEventsBtnExits() {
        btnDXadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    CardView QLtl, QLcm;
    private void addControl() {
        btnDXadmin = findViewById(R.id.btnDXadmin);
        QLtl=findViewById(R.id.QLtl);
        QLcm=findViewById(R.id.QLcm);
        QLtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, TheLoaiActivity.class);
                startActivity(intent);
            }
        });

        QLcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, TruyenActivity.class);
                startActivity(intent);
            }
        });
    }
}