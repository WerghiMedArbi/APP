package com.example.applicationds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbSMQ extends SQLiteOpenHelper {
    public dbSMQ(@Nullable Context context) {
        super(context, "smq", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "create table smq(" +
                        "id integer primary key autoincrement," +
                        "name TEXT," +
                        "score number);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists smq");
    }

    public boolean insertDatabase( String name, String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content= new ContentValues();
        content.put("name",name);
        content.put("score",score);
        long res = db.insert("smq",null,content);

        if (res== -1)
            return false;
        else
            return true;

    }
    public  Cursor showDatabase(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from smq order by score desc", null);
        return c;
    }

}
