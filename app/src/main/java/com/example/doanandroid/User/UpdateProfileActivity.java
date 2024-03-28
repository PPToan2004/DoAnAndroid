package com.example.doanandroid.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanandroid.DB.DBHelper;
import com.example.doanandroid.R;

public class UpdateProfileActivity extends AppCompatActivity {
    Button btnBackprofile,btnsaveprofile;
    EditText edtName,edtGmail,edtMK;
    private user account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        addControl();
        loadData();
        AddeventsBtn();
    }

    private void AddeventsBtn() {
        btnBackprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProfileActivity.this,ProfileActivity.class);
                intent.putExtra("account",account);
                startActivity(intent);
            }
        });
        btnsaveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DBHelper db= new DBHelper(getApplicationContext());
                        user currentAcount= db.find(account.getId());
                        String newGmail = edtGmail.getText().toString();
                    user temp = db.checkGmail(newGmail);
                        if(!newGmail.equalsIgnoreCase(currentAcount.getGmail())&&temp!=null){
                            Toast.makeText(UpdateProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
return;
                        }

                            currentAcount.setName(edtName.getText().toString());
                            currentAcount.setGmail(edtGmail.getText().toString());
                            String password=edtMK.getText().toString();
                            if(!password.isEmpty()){
                                currentAcount.setPass(edtMK.getText().toString());
                            }
                            if(db.update(currentAcount)){
                                Intent intent = new Intent(UpdateProfileActivity.this,ProfileActivity.class);
                                intent.putExtra("account",currentAcount);
                                startActivity(intent);
                            }else {
                                Toast.makeText(UpdateProfileActivity.this, "Fail", Toast.LENGTH_SHORT).show();

                            }

                }catch (Exception e){
                    Toast.makeText(UpdateProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData() {
        Intent intent = getIntent();
        account = (user) intent.getSerializableExtra("account");
            edtName.setText(account.getName());
            edtGmail.setText(account.getGmail());
            edtMK.setText(account.getPass());

    }


    private void addControl() {
        btnBackprofile = findViewById(R.id.btnBackprofile);
        btnsaveprofile = findViewById(R.id.btnsaveprofile);
        edtName = findViewById(R.id.edtName);
        edtGmail = findViewById(R.id.edtGmail);
        edtMK = findViewById(R.id.edtMK);

    }
}