package com.applex.icd__to__excel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper_3 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="Part_3_I.db";
    private static final String TABLE_NAME = "Part_3_I_data";
    private static final String col2 = "CODE";

    public DatabaseHelper_3(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + col2 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean addData(String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, link);

        long result = db.insert(TABLE_NAME,null, contentValues);
        return result != -1;
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        String clearDBQuery = "DELETE FROM " + TABLE_NAME;
        db.execSQL(clearDBQuery);
    }

    public ArrayList<String> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if(cursor.getCount() < 1)
            return null;

        ArrayList<String> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(cursor.getColumnIndex(col2)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return data;
    }
}