package com.itheima.rbclient.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cuiruolei on 2016/8/3.
 */

public class SearchHistoryOpenHelper extends SQLiteOpenHelper {
    public SearchHistoryOpenHelper(Context context){
        super(context, "search_history", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table history (_id integer primary key autoincrement,recode varchar(20) unique)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
