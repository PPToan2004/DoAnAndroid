package com.example.doanandroid.DB.Truyen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.DB.Theloai.TheLoai;
import com.example.doanandroid.DB.Theloai.TheloaiDataQuery;
import com.example.doanandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class TruyenActivity extends AppCompatActivity implements TruyenAdapter.UserCallback {
    TextView tvHeaderC;
    RecyclerView rcListCode;
    ArrayList<Truyen> lstUser;
    TruyenAdapter userAdapter;
    FloatingActionButton fbAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);
        tvHeaderC = findViewById(R.id.tvHeader);
        rcListCode = findViewById(R.id.rvList);
        fbAdd = findViewById(R.id.fbAdd);
        fbAdd.setOnClickListener(view -> addTruyenDialog());

        lstUser = new ArrayList<>();
        lstUser = TruyenDataQuery.getAll(this);
        userAdapter = new TruyenAdapter(lstUser);
        userAdapter.setUserCallback((TruyenAdapter.UserCallback) this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcListCode.setAdapter(userAdapter);
        rcListCode.setLayoutManager(linearLayoutManager);
    }
    void addTruyenDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TruyenActivity.this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_truyen, null);
        alertDialog.setView(dialogView);

        EditText edName = dialogView.findViewById(R.id.edName);
        EditText edAvatar = dialogView.findViewById(R.id.edAvatar);

        Spinner snPart= dialogView.findViewById(R.id.typeSpinner);
        ArrayList<TheLoai> lstType = TheloaiDataQuery.getAll(this);
        lstType.add(0,new TheLoai(0, "Chọn thể loại"));
        ArrayAdapter<TheLoai> typeArrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstType);
        typeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snPart.setAdapter(typeArrayAdapter);

        alertDialog.setPositiveButton("Đồng ý", ((dialog, which) ->
        {
            String name = edName.getText().toString();
            String avatar = edAvatar.getText().toString();
            TheLoai itemType = (TheLoai) snPart.getSelectedItem();
            if (itemType.getId() == 0)
            {
                Toast.makeText(TruyenActivity.this, "Vui lòng chọn thể loại", Toast.LENGTH_LONG).show();
            } else
            if (name.isEmpty())
                Toast.makeText(TruyenActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            else {
                Truyen user = new Truyen(0, name, avatar);
                user.theloai_ID = itemType.getId();
                long id = TruyenDataQuery.insert(TruyenActivity.this, user);
                if (id > 0) {
                    Toast.makeText(TruyenActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        }
        ));
        alertDialog.setNegativeButton("Hủy", ((dialog, which) ->
        {
            dialog.dismiss();
        }
        ));
        alertDialog.create();
        alertDialog.show();
    }
    void updateTruyenDialog(Truyen truyen)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TruyenActivity.this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_truyen, null);
        alertDialog.setView(dialogView);

        EditText edName = dialogView.findViewById(R.id.edName);
        EditText edAvatar = dialogView.findViewById(R.id.edAvatar);

        Spinner snPart= dialogView.findViewById(R.id.typeSpinner);
        ArrayList<TheLoai> lstType = TheloaiDataQuery.getAll(this);
        lstType.add(0,new TheLoai(0, "Chọn thể loại"));
        ArrayAdapter<TheLoai> typeArrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstType);
        typeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snPart.setAdapter(typeArrayAdapter);

        alertDialog.setPositiveButton("Đồng ý", ((dialog, which) ->
        {
            String name = edName.getText().toString();
            String avatar = edAvatar.getText().toString();
            TheLoai itemType = (TheLoai) snPart.getSelectedItem();
            if (itemType.getId() == 0)
            {
                Toast.makeText(TruyenActivity.this, "Vui lòng chọn thể loại", Toast.LENGTH_LONG).show();
            } else
            if (name.isEmpty())
                Toast.makeText(TruyenActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            else {
                Truyen user = new Truyen(0, name, avatar);
                user.theloai_ID = itemType.getId();
                long id = TruyenDataQuery.insert(TruyenActivity.this, user);
                if (id > 0) {
                    Toast.makeText(TruyenActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        }
        ));
        alertDialog.setNegativeButton("Hủy", ((dialog, which) ->
        {
            dialog.dismiss();
        }
        ));
        alertDialog.create();
        alertDialog.show();
    }

    void resetData() {
        lstUser.clear();
        lstUser.addAll(TruyenDataQuery.getAll(TruyenActivity.this));
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void ItemNameClicked(Truyen user, int position) {
        Intent i = new Intent(TruyenActivity.this, Truyen_Detail_Activity.class);
        i.putExtra("TruyenDetail", (Serializable) lstUser.get(position));
        startActivity(i);
    }

    @Override
    public void onItemDeleteClicked(Truyen user, int position) {
        boolean rs = TruyenDataQuery.delete(TruyenActivity.this, user.id);
        if (rs) {
            Toast.makeText(this, "Xoá thành công", Toast.LENGTH_LONG).show();
            resetData();
        } else {
            Toast.makeText(this, "Xoá thất bại", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemEditClicked(Truyen user, int position) {
        updateTruyenDialog(user);
    }
}