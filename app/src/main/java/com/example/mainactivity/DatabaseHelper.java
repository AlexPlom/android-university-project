package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import data.BmiModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "indexes";
    private static final String COL1 = "ID";
    private static final String COL2 = "bmi";
    private static final String COL3 = "height";
    private static final String COL4 = "weight";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, bmi REAL, height REAL, weight REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(Double bmi, Double height, Double weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, bmi);
        contentValues.put(COL3, height);
        contentValues.put(COL4, weight);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }

        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY ID DESC", null);

        return data;
    }

    public ArrayList<BmiModel> getDataCorrectly() {
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY ID DESC", null);
        ArrayList<BmiModel> listData = new ArrayList<>();

        while(data.moveToNext()){
            BmiModel model = new BmiModel();
            model.setHeight(data.getDouble(data.getColumnIndex(COL3)));
            model.setWeight(data.getDouble(data.getColumnIndex(COL4)));
            model.setResult(data.getDouble(data.getColumnIndex(COL2)));
            model.setId(data.getInt(data.getColumnIndex(COL1)));
            listData.add(model);
        }
        return listData;
    }

    public void deleteHistory(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
