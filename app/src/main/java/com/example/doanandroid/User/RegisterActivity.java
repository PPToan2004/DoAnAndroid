package com.example.doanandroid.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanandroid.DB.DBHelper;
import com.example.doanandroid.R;

public class RegisterActivity extends AppCompatActivity {
    EditText edtGmail,edtPass,edtRePass,edtUsername;
    Button btnSignup, btnThoat;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addEvents();
        checkEmail();
    }

    private void checkEmail() {
        edtGmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    btnSignup.setEnabled(true);
                    edtGmail.setError(null); // Clear error if any
                } else {
                    btnSignup.setEnabled(false);
                    edtGmail.setError("Invalid Email Address");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void addEvents() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gmail = edtGmail.getText().toString();
                String pass = edtPass.getText().toString();
                String repass = edtRePass.getText().toString();
                String username = edtUsername.getText().toString();

                if (gmail.isEmpty() || pass.isEmpty() || repass.isEmpty() || username.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    db = new DBHelper(RegisterActivity.this);
                    user account = new user();
                    account.setGmail(gmail);
                    account.setPass(pass);
                    account.setName(username);

                    user temp = db.checkGmail(gmail);
                    if (temp == null) {
                        if (pass.equals(repass)) {
                            if (db.create(account)) {
                                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                // Gửi dữ liệu người dùng sang hoạt động hồ sơ
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                                startActivity(intent);
                                finish();  // Finish this activity to prevent going back to it with the back button
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addControls() {
        edtGmail = findViewById(R.id.edtGmail);
        edtPass = findViewById(R.id.edtPass);
        edtRePass = findViewById(R.id.edtRePass);
        edtUsername = findViewById(R.id.edtusername);
        btnSignup = findViewById(R.id.btnsignup);
        btnThoat = findViewById(R.id.btnThoat);
    }
}
