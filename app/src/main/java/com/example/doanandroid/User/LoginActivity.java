package com.example.doanandroid.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanandroid.Admin.AdminActivity;
import com.example.doanandroid.DB.DBHelper;
import com.example.doanandroid.MainActivity;
import com.example.doanandroid.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtGmail1,edtPass1;
    Button btnlogin,btnResgister;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(getApplicationContext());
                String gmail= edtGmail1.getText().toString();
                String pass = edtPass1.getText().toString();
                user account = db.login(gmail,pass);
                if(gmail.equals("")||pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (gmail.equals("admin2004@gmail.com") && pass.equals("adminpassword")) {
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                    }
                    else if (account != null) {
                        Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class); // Chuyển đến ProfileActivity
                        intent.putExtra("account", account);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        btnResgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        edtGmail1 = findViewById(R.id.edtGmail1);
        edtPass1 = findViewById(R.id.edtPass1);
        btnlogin = findViewById(R.id.btnlogin);
        btnResgister = findViewById(R.id.btnResgister);
        db=new DBHelper(getApplicationContext());
    }

}
