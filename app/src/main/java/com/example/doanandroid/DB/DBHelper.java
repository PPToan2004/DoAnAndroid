package com.example.doanandroid.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.doanandroid.User.user;
import com.example.doanandroid.utils;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = utils.DATABASE_NAME;
    private  static final int DATABASE_VERSION = 9;

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
    public boolean create(user account) {
        boolean result = true;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(utils.COLUMN_CUSTOMER_GMAIL, account.getGmail());
            contentValues.put(utils.COLUMN_CUSTOMER_PASSWORD, account.getPass());
            contentValues.put(utils.COLUMN_CUSTOMER_NAME, account.getName());
            long insertResult = db.insert(utils.TABLE_CUSTOMER, null, contentValues);
            Log.d("DBHelper", "Insert result: " + insertResult);
            result = insertResult != -1;
        } catch (Exception e) {
            Log.e("DBHelper", "Error creating user: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public user login(String gmail,String password) {
        user account = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + utils.TABLE_CUSTOMER + " WHERE " + utils.COLUMN_CUSTOMER_GMAIL + " = ? AND " + utils.COLUMN_CUSTOMER_PASSWORD + " = ?", new String[]{gmail, password});
            if (cursor.moveToFirst()) {
                account = new user();
                account.setId(cursor.getInt(0));
                account.setGmail(cursor.getString(1));
                account.setPass(cursor.getString(2));
                account.setName(cursor.getString(3));
            }
            cursor.close();
        } catch (Exception e) {
            account = null;
        }
        return account;
    }

    public user checkGmail(String gmail) {
        user account = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + utils.TABLE_CUSTOMER + " WHERE " + utils.COLUMN_CUSTOMER_GMAIL + " = ? ", new String[]{gmail});
            if (cursor.moveToFirst()) {
                account = new user();
                account.setId(cursor.getInt(0));
                account.setGmail(cursor.getString(1));
                account.setPass(cursor.getString(2));
                account.setName(cursor.getString(3));
            }
            cursor.close();
        } catch (Exception e) {
            account = null;
        }
        return account;
    }
    public boolean update   (user account) {
        boolean result = true;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(utils.COLUMN_CUSTOMER_GMAIL, account.getGmail());
            contentValues.put(utils.COLUMN_CUSTOMER_PASSWORD, account.getPass());
            contentValues.put(utils.COLUMN_CUSTOMER_NAME, account.getName());
            result = db.update(utils.TABLE_CUSTOMER,contentValues,utils.COLUMN_CUSTOMER_ID+"=?"
            ,new String[]{String.valueOf(account.getId())})>0;
        } catch (Exception e) {
            Log.e("DBHelper", "Error creating user: " + e.getMessage());
            result = false;
        }
        return result;
    }
    public user find (int id) {
        user account = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + utils.TABLE_CUSTOMER + " WHERE " + utils.COLUMN_CUSTOMER_ID + " = ? ", new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()) {
                account = new user();
                account.setId(cursor.getInt(0));
                account.setGmail(cursor.getString(1));
                account.setPass(cursor.getString(2));
                account.setName(cursor.getString(3));
            }
            cursor.close();
        } catch (Exception e) {
            account = null;
        }
        return account;
    }

}
