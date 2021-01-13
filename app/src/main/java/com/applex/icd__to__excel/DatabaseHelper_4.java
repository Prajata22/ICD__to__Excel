package com.applex.icd__to__excel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper_4 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="Part_XX.db";
    private static final String TABLE_NAME = "ICD_CODES_PART_XX";
    private static final String col2 = "GROUPID";
    private static final String col3 = "GROUPNAME";
    private static final String col4 = "OUTERCODEGROUPID";
    private static final String col5 = "OUTERGROUPNAME";
    private static final String col6 = "INNERCODEGROUPID";
    private static final String col7 = "INNERGROUPNAME";
    private static final String col8 = "ICDCODE";
    private static final String col9 = "CODEDESCRIPTION";

    public DatabaseHelper_4(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col2 + " TEXT, " + col3 + " TEXT, " + col4 + " TEXT, " + col5 + " TEXT, " + col6 + " TEXT, "
                + col7 + " TEXT, " + col8 + " TEXT, " + col9 + " TEXT)";
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

    public boolean addData(String group_id, String group_name, String outer_group_id, String outer_group_name,
                           String inner_group_id, String inner_group_name, String icd_code, String code_description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, group_id);
        contentValues.put(col3, group_name);
        contentValues.put(col4, outer_group_id);
        contentValues.put(col5, outer_group_name);
        contentValues.put(col6, inner_group_id);
        contentValues.put(col7, inner_group_name);
        contentValues.put(col8, icd_code);
        contentValues.put(col9, code_description);

        long result = db.insert(TABLE_NAME,null, contentValues);
        return result != -1;
    }
}