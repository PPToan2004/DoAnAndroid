package com.example.doanandroid.User;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doanandroid.R;

import java.util.ArrayList;

public class userAdapter extends BaseAdapter {
    //khai báo biến
    Context context ;
    int layout;
    ArrayList<user> ArrayListUser;
    //tạo contructor

    public userAdapter(Context context, int layout, ArrayList<user> arrayListUser) {
        this.context = context;
        this.layout = layout;
        ArrayListUser = arrayListUser;
    }

    @Override
    // kích thước của danh sách
    public int getCount() {
        return ArrayListUser.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

public class ViewHolder{
        TextView txtName,txtGmail,txtPass;
}
    @Override
    //lấy ra 1 user tại vị trí nào đó
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            //tham chiếu id trong layoutContact->holder
            holder.txtGmail = convertView.findViewById(R.id.txtgmail);
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.txtPass = convertView.findViewById(R.id.txtMK);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        //get data từ from đăng ký -> holder
        user us =ArrayListUser.get(position);
        holder.txtGmail.setText(us.getGmail());
        holder.txtName.setText(us.getName());
        holder.txtPass.setText(us.getPass());
        return convertView;

    }
}
