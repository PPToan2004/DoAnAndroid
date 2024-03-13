package com.example.doanandroid.DB.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.doanandroid.DB.Theloai.TheLoaiActivity;
import com.example.doanandroid.R;

public class AdminActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        addControl();
    }
    CardView QLtl;
    private void addControl() {
        QLtl=findViewById(R.id.QLtl);
        QLtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, TheLoaiActivity.class);
                startActivity(intent);
            }
        });
    }
}