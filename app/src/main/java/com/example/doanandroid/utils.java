package com.example.doanandroid;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class utils {
    public static final String DATABASE_NAME = "db-user";
    public static final String TABLE_TRUYEN = "user";
    public static final String TABLE_THELOAI = "theloai";
    public static final  String COLUMN_THELOAI_ID = "theloai_id";
    public static  final String COLUMN_THELOAI_NAME = "theloai_name";
    public static final String COLUMN_TRUYEN_ID = "truyen_id";
    public static final String COLUMN_TRUYEN_NAME = "truyen_name";
    public static final String COLUMN_TRUYEN_AVATAR = "truyen_avatar";
    public static final String COLUMN_THELOAI_TRUYEN_ID = "truyen_theloai_id";

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
