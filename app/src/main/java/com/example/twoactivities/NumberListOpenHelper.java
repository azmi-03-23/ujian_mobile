package com.example.twoactivities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NumberListOpenHelper extends SQLiteOpenHelper {


    private static final String TAG = NumberListOpenHelper.class.getSimpleName();


    // has to be 1 first time or app will crash
    private static final int DATABASE_VERSION = 1;
    private static final String NUMBER_LIST_TABLE = "number_entries";
    private static final String DATABASE_NAME = "numberlist";
    // Column names...
    public static final String KEY_ID = "_id";
    public static final String KEY_NUMBER = "number";
    // ... and a string array of columns.
    private static final String[] COLUMNS = { KEY_ID, KEY_NUMBER };

    // Build the SQL query that creates the table.
    private static final String NUMBER_LIST_TABLE_CREATE =
            "CREATE TABLE " + NUMBER_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
// id will auto-increment if no value passed
                    KEY_NUMBER + " TEXT NOT NULL);";

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    public NumberListOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NUMBER_LIST_TABLE_CREATE);
        //fillDatabaseWithData(db);
    }

    private void fillDatabaseWithData(SQLiteDatabase db){
        String[] numbers = {"12345", "23456", "3456789", "4567890"};
        ContentValues values = new ContentValues();
        for (int i=0; i < numbers.length; i++) {
            values.put(KEY_NUMBER, numbers[i]);
            db.insert(NUMBER_LIST_TABLE, KEY_NUMBER, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(NumberListOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + NUMBER_LIST_TABLE);
        onCreate(db);
    }

    public long insert(String number){
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(KEY_NUMBER, number);
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(NUMBER_LIST_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
        return newId;
    }

    public String query() {
        String query = "SELECT " + KEY_NUMBER + " FROM " + NUMBER_LIST_TABLE;
        Cursor cursor = null;
        String numbers = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            int r = 0;
            String temp;
            for (int i=0; i<cursor.getCount(); i++) {
                r = cursor.getColumnIndex(KEY_NUMBER);
                temp = cursor.getString(r);
                if(r >= 0 && temp != null){
                    if(numbers != null){
                        numbers = numbers + temp + "\n" ;
                    } else {
                        numbers = temp + "\n";
                    }
                }
                cursor.moveToNext();
            }
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        }
            cursor.close();
            return numbers;
    }

}

