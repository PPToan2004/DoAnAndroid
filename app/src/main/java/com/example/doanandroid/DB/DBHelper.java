package com.example.doanandroid.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.doanandroid.utils;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = utils.DATABASE_NAME;
    private  static final int DATABASE_VERSION = 2;

    public DBHelper( Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRUYEN_TABLE = "CREATE TABLE " + utils.TABLE_TRUYEN + " ("
                + utils.COLUMN_TRUYEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + utils.COLUMN_TRUYEN_NAME + " TEXT, "
                + utils.COLUMN_TRUYEN_AVATAR + " TEXT,"
                + utils.COLUMN_THELOAI_TRUYEN_ID + " TEXT"
                + ");";

        String CREATE_THELOAI_TABLE = "CREATE TABLE " + utils.TABLE_THELOAI + " ("
                + utils.COLUMN_THELOAI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + utils.COLUMN_THELOAI_NAME + " TEXT" + ");" ;
        db.execSQL(CREATE_TRUYEN_TABLE);
        db.execSQL(CREATE_THELOAI_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + utils.TABLE_TRUYEN);
        db.execSQL("DROP TABLE IF EXISTS " + utils.TABLE_THELOAI);
        onCreate(db);
    }
}
