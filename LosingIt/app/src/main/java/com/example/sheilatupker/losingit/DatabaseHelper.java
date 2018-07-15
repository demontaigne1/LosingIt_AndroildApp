package com.example.sheilatupker.losingit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    //constant names
    public static final String DATABASE_NAME = "weights.db";
    public static final String TABLE_NAME = "weights";
    public static final String COL_1 = "id";
    public static final String COL_2 = "weight";
    public static final String COL_3 = "name";
    public static final String COL_4 = "picture";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    // two languages being spoken below - Java and SQL
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                " (id integer primary key autoincrement, " +
                "weight integer," +
                "name text," +
                "picture text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(Integer weight, String name, String picture){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, weight);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, picture);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

   /* public String getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM weights", null);
        if(res.getCount() == 0){
            return "Nothing Found";
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(3) + "\n");
        }
        return buffer.toString();


    }
    */

   public String [] getInfo(String wentered){
       try {
           SQLiteDatabase db = this.getWritableDatabase(); //getting access to the db
           //doing SQL in JAVA to pull from the db and storing it in the var res
           Cursor res = db.rawQuery("SELECT picture, name FROM weights WHERE weight = ? LIMIT 1", new String[]{wentered});
           //if nothing is returned - take care of business
           if (res.getCount() == 0) {
               return  new String [] { "","Sorry, nothing found."};
           //pull the requested data
           } else {
               //even though I have limited to 1 row sql knows this but JAVA doesn't know this so I need this code here
               res.moveToNext();
               //now actually grab the data remember these columns are the ones returned from the db
               return new String[]{res.getString(0), "Congratulations, you have lost a " + res.getString(1) + "!"};
           }
       } catch(Exception e){
           return new String [] {"", e.toString()} ;
       }
   }




    public void clearAllLData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);

    }
}


