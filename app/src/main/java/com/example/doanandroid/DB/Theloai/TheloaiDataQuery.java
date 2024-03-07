package com.example.doanandroid.DB.Theloai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doanandroid.DB.DBHelper;
import com.example.doanandroid.utils;

import java.util.ArrayList;

public class TheloaiDataQuery {
    public static long insert(Context context, TheLoai theLoai)
    {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase =db.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(utils.COLUMN_THELOAI_NAME, theLoai.name);
        long rs =sqLiteDatabase.insert(utils.TABLE_THELOAI, null, values);
        return(rs);
    }
    public static ArrayList<TheLoai> getAll(Context context)
    {
        ArrayList<TheLoai> lstTheloai = new ArrayList<>();
        DBHelper userDBHelper = new DBHelper(context);
        SQLiteDatabase db = userDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + utils.TABLE_THELOAI, null);
        while (cs.moveToNext())
        {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            TheLoai theLoai = new TheLoai(id, name);
            lstTheloai.add(theLoai);
        }
        cs.close();
        db.close();
        return lstTheloai;
    }
    public static boolean delete(Context context, int id)
    {
        DBHelper userDBHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(utils.TABLE_THELOAI, utils.COLUMN_THELOAI_ID+"=?", new String[]{String.valueOf(id)});
        return (rs > 0);
    }
    public static int update(Context context, TheLoai type)
    {
        DBHelper userDBHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utils.COLUMN_THELOAI_NAME, type.name);
        int rs = sqLiteDatabase.update(utils.TABLE_THELOAI, values, utils.COLUMN_THELOAI_ID+"=?", new String[] {String.valueOf(type.id)} );
        return (rs);
    }
}
