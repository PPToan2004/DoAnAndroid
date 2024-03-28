package com.example.doanandroid.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.doanandroid.utils;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = utils.DATABASE_NAME;
    private  static final int DATABASE_VERSION = 8;

    public DBHelper( Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + utils.TABLE_CUSTOMER + " ("
                + utils.COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + utils.COLUMN_CUSTOMER_GMAIL + " TEXT, "
                + utils.COLUMN_CUSTOMER_PASSWORD + " TEXT, "
                + utils.COLUMN_CUSTOMER_NAME + " TEXT,"
                + utils.COLUMN_CUSTOMER_ROLE + " TEXT"
                +");";

        String CREATE_TRUYEN_TABLE = "CREATE TABLE " + utils.TABLE_TRUYEN + " ("
                + utils.COLUMN_TRUYEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + utils.COLUMN_TRUYEN_NAME + " TEXT, "
                + utils.COLUMN_TRUYEN_AVATAR + " TEXT,"
                + utils.COLUMN_THELOAI_TRUYEN_ID + " TEXT"
                + ");";

        String CREATE_THELOAI_TABLE = "CREATE TABLE " + utils.TABLE_THELOAI + " ("
                + utils.COLUMN_THELOAI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + utils.COLUMN_THELOAI_NAME + " TEXT" + ");" ;


        String CREATE_CHAPTER_TABLE = "CREATE TABLE " + utils.TABLE_CHAPTER + " ("
                + utils.COLUMN_CHAPTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + utils.COLUMN_CHAPTER_NAME + " TEXT, "
                + utils.COLUMN_CHAPTER_STT + " INTEGER, "
                + utils.COLUMN_CHAPTER_CONTENT + " TEXT, "
                + utils.COLUMN_CHAPTER_ID_TRUYEN + " TEXT"
                + ");";
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_TRUYEN_TABLE);
        db.execSQL(CREATE_THELOAI_TABLE);
        db.execSQL(CREATE_CHAPTER_TABLE);
        ContentValues adminvalues = new ContentValues();
        adminvalues.put(utils.COLUMN_CUSTOMER_GMAIL,"admin2004@gmail.com");
        adminvalues.put(utils.COLUMN_CUSTOMER_PASSWORD,"adminpassword");
        adminvalues.put(utils.COLUMN_CUSTOMER_ROLE,"admin");
        db.insert(utils.TABLE_CUSTOMER, null, adminvalues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + utils.TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + utils.TABLE_TRUYEN);
        db.execSQL("DROP TABLE IF EXISTS " + utils.TABLE_THELOAI);
        db.execSQL("DROP TABLE IF EXISTS " + utils.TABLE_CHAPTER);
        onCreate(db);
    }
    public Boolean insertData(String gmail, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(utils.COLUMN_CUSTOMER_GMAIL, gmail);
        contentValues.put(utils.COLUMN_CUSTOMER_PASSWORD, password);
        long result = db.insert(utils.TABLE_CUSTOMER, null, contentValues);
        if(result==-1)
        {
            return  false;
        }
        else return true;
    }
    public Boolean checkgmail(String gmail){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + utils.TABLE_CUSTOMER + " WHERE " + utils.COLUMN_CUSTOMER_GMAIL + " = ?", new String[]{gmail});
        if (cursor.getCount()>0)
            return  true;
        else
            return  false;
    }
    public Boolean checkgmailpass(String gmail,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + utils.TABLE_CUSTOMER + " WHERE " + utils.COLUMN_CUSTOMER_GMAIL + " = ? AND " + utils.COLUMN_CUSTOMER_PASSWORD + " = ?", new String[]{gmail, password});
        if (cursor.getCount()>0)
            return  true;
        else
            return  false;
    }
    public boolean checkusertype(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + utils.TABLE_CUSTOMER + " WHERE " + utils.COLUMN_CUSTOMER_ROLE + " = ?", new String[]{});
        if (cursor.getCount()>0)
            return  true;
        else
            return  false;
    }

}
