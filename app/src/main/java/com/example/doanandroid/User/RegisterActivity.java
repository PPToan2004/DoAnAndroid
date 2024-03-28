package com.example.doanandroid.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    EditText edtGmail,edtPass,edtRePass,edtusername;
    Button btnsignup,btnThoat;
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(Patterns.EMAIL_ADDRESS.matcher(edtGmail.getText().toString()).matches()){
                btnsignup.setEnabled(true);
            }
            else {
                btnsignup.setEnabled(false);
                edtGmail.setError("Invalid EmailAddress");
            }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addEvents() {
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gmail = edtGmail.getText().toString();
                String pass = edtPass.getText().toString();
                String repass = edtRePass.getText().toString();
                String user = edtusername.getText().toString();

                    if (gmail.equals("")|| pass.equals("")|| repass.equals("")|| user.equals(""))
                    {
                        Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(pass.equals(repass)){
                            Boolean checkgmail = db.checkgmail(gmail);
                            if (checkgmail==false) {
                                Boolean insert = db.insertData(gmail,pass);
                                if (insert==true){
                                    Toast.makeText(RegisterActivity.this,"Registered seccessfull",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }   else {
                                    Toast.makeText(RegisterActivity.this,"Registered failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(RegisterActivity.this,"Please check gmail",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(RegisterActivity.this,"Please check pass",Toast.LENGTH_SHORT).show();
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
        edtGmail= findViewById(R.id.edtGmail);
        edtPass= findViewById(R.id.edtPass);
        edtRePass= findViewById(R.id.edtRePass);
        edtusername= findViewById(R.id.edtusername);
        btnsignup= findViewById(R.id.btnsignup);
        btnThoat= findViewById(R.id.btnThoat);
        db = new DBHelper(this);
    }

}