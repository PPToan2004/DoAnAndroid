package com.example.doanandroid;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class utils {
    public static final String DATABASE_NAME = "db-user";

    public static final String TABLE_CUSTOMER = "CUSTOMER";
    public static final String TABLE_TRUYEN = "truyen";
    public static final String TABLE_THELOAI = "theloai";
    public static final String TABLE_CHAPTER = "chapter";

    public static final  String COLUMN_CUSTOMER_ID = "CUSTOMER_ID";
    public static final  String COLUMN_CUSTOMER_GMAIL = "CUSTOMER_GMAIL";
    public static final  String COLUMN_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    public static final  String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final  String COLUMN_CUSTOMER_ROLE = "CUSTOMER_ROLE";


    public static final  String COLUMN_THELOAI_ID = "theloai_id";
    public static  final String COLUMN_THELOAI_NAME = "theloai_name";

    public static final String COLUMN_TRUYEN_ID = "truyen_id";
    public static final String COLUMN_TRUYEN_NAME = "truyen_name";
    public static final String COLUMN_TRUYEN_AVATAR = "truyen_avatar";
    public static final String COLUMN_THELOAI_TRUYEN_ID = "truyen_theloai_id";

    public static final String COLUMN_CHAPTER_ID = "chapter_id";
    public static final String COLUMN_CHAPTER_NAME = "chapter_name";
    public static final String COLUMN_CHAPTER_CONTENT = "chapter_content";
    public static final String COLUMN_CHAPTER_ID_TRUYEN = "chapter_id_truyen";
    public static final String COLUMN_CHAPTER_STT = "chapter_stt";

    public static Bitmap convertToBitMapFromAssets(Context context, String nameImage)
    {
        AssetManager assetManager = context.getAssets();
        try{
            InputStream inputStream = assetManager.open("images/" + nameImage);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return  bitmap;
        } catch (IOException e){
            e.printStackTrace();
        }
        return  null;
    }

}
