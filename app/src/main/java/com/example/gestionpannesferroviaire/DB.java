package com.example.gestionpannesferroviaire;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    private static final String DBNAME = "MAPS";
    private static final String TABLE = "INFORMATIONS";
    private static final String TABLE1 = "MAINTENANCE";
    private static final int VER = 1;
    public DB(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + TABLE + "(id integer primary key, Latitude text, Longitude text, Date text, time text)";
        sqLiteDatabase.execSQL(query);
        String query1 = "create table " + TABLE1 + "(id_m integer primary key, Latitude text, Longitude text, DateREP text)";
        sqLiteDatabase.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "drop table if exists " + TABLE + "";
        String query1 = "drop table if exists " + TABLE1 + "";
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query1);
        onCreate(sqLiteDatabase);
    }
}
