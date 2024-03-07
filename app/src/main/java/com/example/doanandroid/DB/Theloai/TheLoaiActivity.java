package com.example.doanandroid.DB.Theloai;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TheLoaiActivity extends AppCompatActivity implements TheloaiAdapter.TheloaiCallback {
    TextView tvHeaderC;
    RecyclerView rcListCode;
    ArrayList<TheLoai> lstType;
    TheloaiAdapter theloaiAdapter;
    FloatingActionButton fbTypeAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        tvHeaderC = findViewById(R.id.tvTypeHeader);
        rcListCode = findViewById(R.id.rvTypeList);
        fbTypeAdd = findViewById(R.id.fbTypeAdd);
        fbTypeAdd.setOnClickListener(view -> addTheloaiDialog());

        lstType = new ArrayList<>();
        lstType = TheloaiDataQuery.getAll(this);
        theloaiAdapter = new TheloaiAdapter(lstType);
        theloaiAdapter.setTypeCallBack((TheloaiAdapter.TheloaiCallback) this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcListCode.setAdapter(theloaiAdapter);
        rcListCode.setLayoutManager(linearLayoutManager);
    }
    void addTheloaiDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TheLoaiActivity.this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_theloai, null);
        alertDialog.setView(dialogView);
        EditText edTypeName = dialogView.findViewById(R.id.edTypeName);
        alertDialog.setPositiveButton("Đồng ý", ((dialog, which) ->
        {
            String name = edTypeName.getText().toString();
            if (name.isEmpty())
                Toast.makeText(TheLoaiActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            else {
                TheLoai type = new TheLoai(0, name);
                long id = TheloaiDataQuery.insert(TheLoaiActivity.this, type);
                if (id > 0) {
                    Toast.makeText(TheLoaiActivity.this, "Thêm loại thành công.", Toast.LENGTH_LONG).show();
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
        lstType.clear();
        lstType.addAll(TheloaiDataQuery.getAll(TheLoaiActivity.this));
        theloaiAdapter.notifyDataSetChanged();
    }
    void updateTheloaiDialog(TheLoai theloai)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TheLoaiActivity.this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_theloai, null);
        alertDialog.setView(dialogView);
        EditText edTypeName = dialogView.findViewById(R.id.edTypeName);
        alertDialog.setPositiveButton("Đồng ý", ((dialog, which) ->
        {
            String name = edTypeName.getText().toString();
            if (name.isEmpty())
                Toast.makeText(TheLoaiActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            else {
                TheLoai type = new TheLoai(0, name);
                long id = TheloaiDataQuery.insert(TheLoaiActivity.this, type);
                if (id > 0) {
                    Toast.makeText(TheLoaiActivity.this, "Thêm loại thành công.", Toast.LENGTH_LONG).show();
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
    @Override
    public void onItemDeleteClicked(TheLoai type, int position) {
        boolean rs = TheloaiDataQuery.delete(TheLoaiActivity.this, type.id);
        if (rs) {
            Toast.makeText(this, "Xoá thành công", Toast.LENGTH_LONG).show();
            resetData();
        } else {
            Toast.makeText(this, "Xoá thất bại", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemEditClicked(TheLoai type, int position) {
        updateTheloaiDialog(type);
    }
}