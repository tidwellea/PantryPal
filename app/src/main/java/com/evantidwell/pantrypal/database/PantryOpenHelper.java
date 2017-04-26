package com.evantidwell.pantrypal.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PantryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pantry.db";
    private static final String TABLE_NAME = "food_items";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME_FOODNAME = "FOOD_NAME";
    private static final String COLUMN_NAME_QUANTITY = "QUANTITY";
    private static final String COLUMN_NAME_LOCATION = "LOCATION";

    public PantryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME
                                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,FOOD_NAME TEXT,QUANTITY INTEGER, LOCATION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String foodName, int quantity, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_FOODNAME, foodName);
        contentValues.put(COLUMN_NAME_QUANTITY, quantity);
        contentValues.put(COLUMN_NAME_LOCATION, location);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

    public Cursor getPantryData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE LOCATION='PANTRY'", null);
    }

    public Cursor getFridgeData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE LOCATION='FRIDGE'", null);
    }

    public Cursor getFreezerData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE LOCATION='FREEZER'", null);
    }
}
