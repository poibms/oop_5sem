package com.example.lab9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String Database_Name = "FirstDataBase.db";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "SimpleTable";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_F = "F";
    public static final String COLUMN_T = "T";

    public DataBaseHelper(Context context) {
        super(context, Database_Name, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SimpleTable (" + COLUMN_ID
                + " INTEGER PRIMARY KEY," + COLUMN_F
                + " REAL, " + COLUMN_T + " TEXT);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
