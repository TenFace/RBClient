package com.itheima.rbclient.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.itheima.rbclient.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiruolei on 2016/8/3.
 */

public class SearchHistoryDaoImpl {
    public static long insert(String recode) {
        SearchHistoryOpenHelper historyOpenHelper = new SearchHistoryOpenHelper(App.appliction);
        SQLiteDatabase database = historyOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("recode", recode);
        long row = database.insert("history", null, values);
        App.appliction.getContentResolver().notifyChange(Uri.parse("content://history"),null);
        return row;
    }

    public static long delete(String recode) {
        SearchHistoryOpenHelper historyOpenHelper = new SearchHistoryOpenHelper(App.appliction);
        SQLiteDatabase database = historyOpenHelper.getWritableDatabase();
        int row = database.delete("history", "recode=?", new String[]{recode});
        App.appliction.getContentResolver().notifyChange(Uri.parse("content://history"),null);
        return row;
    }

    public static List<String> query() {
        List<String> histories = new ArrayList<>();
        SearchHistoryOpenHelper historyOpenHelper = new SearchHistoryOpenHelper(App.appliction);
        SQLiteDatabase database = historyOpenHelper.getReadableDatabase();
        Cursor cursor = database.query("history", new String[]{"recode"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            histories.add(cursor.getString(cursor.getColumnIndex("recode")));
        }
        return histories;
    }
}
