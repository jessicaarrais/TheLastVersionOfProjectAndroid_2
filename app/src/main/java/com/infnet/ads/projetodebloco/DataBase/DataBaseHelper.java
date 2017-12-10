package com.infnet.ads.projetodebloco.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pizza.db";
    public static final String TABLE_PIZZA = "pizza_table";
    public static final String TABLE_CLIENT = "client_table";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PIZZA + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price TEXT, description TEXT, category TEXT, subCategory TEXT )");
        db.execSQL("CREATE TABLE " + TABLE_CLIENT + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, street TEXT, city TEXT, uf TEXT, phone TEXT, password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}