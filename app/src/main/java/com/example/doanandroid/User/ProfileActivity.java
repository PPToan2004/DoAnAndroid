package com.example.doanandroid.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.DB.DBHelper;
import com.example.doanandroid.MainActivity;
import com.example.doanandroid.R;

public class ProfileActivity extends AppCompatActivity {
   private TextView txtName,txtgmail,txtMK;
    Button btnfixprofile,btnBackMain,btnDangXuat;
    DBHelper db;
    private user account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        addControls(); // Thêm dòng này để khởi tạo các thành phần điều khiển
        addEventsButton();
        // Nhận dữ liệu người dùng từ Intent
        Intent intent = getIntent();
        account = (user) intent.getSerializableExtra("account");
        // Hiển thị thông tin người dùng trên giao diện
        if (account != null) {
            txtName.setText(account.getName());
            txtgmail.setText(account.getGmail());
            txtMK.setText(account.getPass());
        } else {
            Toast.makeText(this, "User information not available", Toast.LENGTH_SHORT).show();
        }
    }




    private void addEventsButton() {
        btnfixprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), UpdateProfileActivity.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void addControls() {
        txtName = findViewById(R.id.txtName);
        txtgmail = findViewById(R.id.txtgmail);
        txtMK = findViewById(R.id.txtMK);
        btnfixprofile = findViewById(R.id.btnfixprofile);
        btnBackMain = findViewById(R.id.btnBackMain);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        db = new DBHelper(this);
    }
}