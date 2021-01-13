package com.applex.icd__to__excel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper_0 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="Part_0_XX.db";
    private static final String TABLE_NAME = "Part_0_XX_data";
    private static final String col2 = "GROUPID";
    private static final String col3 = "GROUPNAME";
    private static final String col4 = "OUTERCODEGROUPID";

    public DatabaseHelper_0(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col2 + " TEXT, " + col3 + " TEXT, " + col4 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean addData(String group_id, String group_name, String outer_group_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, group_id);
        contentValues.put(col3, group_name);
        contentValues.put(col4, outer_group_id);

        long result = db.insert(TABLE_NAME,null, contentValues);
        return result != -1;
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        String clearDBQuery = "DELETE FROM " + TABLE_NAME;
        db.execSQL(clearDBQuery);
    }

    public ArrayList<String> getData(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.query(TABLE_NAME, null, "OUTERCODEGROUPID=?", new String[]{code}, null, null, null);
        if(cursor.getCount() < 1)
            return null;
        cursor.moveToFirst();

        ArrayList<String> data = new ArrayList<>();
        data.add(0, cursor.getString(cursor.getColumnIndex(col2)));
        data.add(1, cursor.getString(cursor.getColumnIndex(col3)));

        return data;
    }

    public ArrayList<String> getCode() {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if(cursor.getCount() < 1)
            return null;

        ArrayList<String> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(cursor.getColumnIndex(col4)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return data;
    }
}