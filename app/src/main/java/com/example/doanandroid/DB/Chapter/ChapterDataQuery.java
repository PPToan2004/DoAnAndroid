package com.example.doanandroid.DB.Chapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doanandroid.DB.DBHelper;
import com.example.doanandroid.utils;

import java.util.ArrayList;

public class ChapterDataQuery {
    public static long insert(Context context, Chapter chapter)
    {
        DBHelper userDBHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utils.COLUMN_CHAPTER_STT, chapter.stt);
        values.put(utils.COLUMN_CHAPTER_NAME, chapter.name);
        values.put(utils.COLUMN_CHAPTER_CONTENT, chapter.content);
        values.put(utils.COLUMN_CHAPTER_ID_TRUYEN, chapter.id_truyen);
        long rs = sqLiteDatabase.insert(utils.TABLE_CHAPTER, null, values);
        return (rs);
    }
    public static ArrayList<Chapter> getAll(Context context)
    {
        ArrayList<Chapter> lstChapter = new ArrayList<>();
        DBHelper userDBHelper = new DBHelper(context);
        SQLiteDatabase db = userDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select chap.*, tr.truyen_id as truyenName from chapter chap left join truyen tr on chap.chapter_id_truyen = tr.truyen_id",
                null);
        cs.moveToFirst();
        while (!cs.isAfterLast())
        {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            int stt = cs.getInt(2);
            String content= cs.getString(3);
            Chapter chapter = new Chapter(id, name, content, stt);
            chapter.setId_truyen(cs.getInt(4));
            lstChapter.add(chapter);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstChapter;
    }
    public static boolean delete(Context context, int id)
    {
        DBHelper userDBHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(utils.TABLE_CHAPTER, utils.COLUMN_CHAPTER_ID+"=?", new String[]{String.valueOf(id)}) ;
        return (rs > 0);
    }
    public static int update(Context context, Chapter chapter)
    {
        DBHelper userDBHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utils.COLUMN_CHAPTER_STT, chapter.stt);
        values.put(utils.COLUMN_CHAPTER_NAME, chapter.name);
        values.put(utils.COLUMN_CHAPTER_CONTENT, chapter.content);
        values.put(utils.COLUMN_CHAPTER_ID_TRUYEN, chapter.id_truyen);
        int rs = sqLiteDatabase.update(utils.TABLE_CHAPTER, values, utils.COLUMN_CHAPTER_ID+"=?", new String[] {String.valueOf(chapter.id)} );
        return (rs);
    }
}
