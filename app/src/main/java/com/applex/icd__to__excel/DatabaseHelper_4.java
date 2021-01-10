package com.applex.icd__to__excel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper_4 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="Part_I.db";
    private static final String TABLE_NAME = "ICD_CODES_PART_I";
    private static final String col2 = "CODE";
    private static final String col3 = "DESCRIPTION";

    public DatabaseHelper_4(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + col2 + " TEXT, " + col3 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        String clearDBQuery = "DELETE FROM " + TABLE_NAME;
        db.execSQL(clearDBQuery);
    }

    public boolean addData(String code, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, code);
        contentValues.put(col3, description);

        long result = db.insert(TABLE_NAME,null, contentValues);
        return result != -1;
    }
}