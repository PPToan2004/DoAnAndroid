package com.example.doanandroid.DB.Truyen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doanandroid.DB.DBHelper;
import com.example.doanandroid.utils;

import java.util.ArrayList;

public class TruyenDataQuery {
    public static long insert(Context context,  Truyen truyen)
    {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utils.COLUMN_TRUYEN_NAME, truyen.name);
        values.put(utils.COLUMN_TRUYEN_AVATAR, truyen.avatar);
        values.put(utils.COLUMN_THELOAI_TRUYEN_ID, truyen.theloai_ID);
        long rs = sqLiteDatabase.insert(utils.TABLE_TRUYEN, null, values);
        return (rs);
    }
    public static ArrayList<Truyen> getAll(Context context)
    {
        ArrayList<Truyen> lstUser = new ArrayList<>();
        DBHelper userDBHelper = new DBHelper(context);
        SQLiteDatabase db = userDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select truyen.*, theloai.theloai_name as typeName from truyen left join theloai on truyen.truyen_theloai_id = theloai.theloai_id",
                null);
        cs.moveToFirst();
        while (!cs.isAfterLast())
        {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String avatar = cs.getString(2);
            Truyen user = new Truyen(id, name, avatar);
            user.theloai_ID = cs.getInt(3);
            user.theloai_Name = cs.getString(4);
            lstUser.add(user);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstUser;
    }
    public static boolean delete(Context context, int id)
    {
        DBHelper userDBHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(utils.TABLE_TRUYEN, utils.COLUMN_TRUYEN_ID+"=?", new String[]{String.valueOf(id)}) ;
        return (rs > 0);
    }
    public static int update(Context context, Truyen user)
    {
        DBHelper userDBHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utils.COLUMN_TRUYEN_NAME, user.name);
        values.put(utils.COLUMN_TRUYEN_AVATAR, user.avatar);
        values.put(utils.COLUMN_THELOAI_TRUYEN_ID, user.theloai_ID);
        int rs = sqLiteDatabase.update(utils.TABLE_TRUYEN, values, utils.COLUMN_TRUYEN_ID+"=?", new String[] {String.valueOf(user.id)} );
        return (rs);
    }
}
