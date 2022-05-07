package com.example.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "Friends.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create Table FriendsData(mobile TEXT primary key, name TEXT , email TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
        myDb.execSQL("drop Table if exists FriendsData");
    }

    public boolean insertUserData(String mobile, String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mobile" , mobile);
        contentValues.put("name" , name);
        contentValues.put("email" , email);

        long result = db.insert("FriendsData", null,contentValues );
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean updateUserData(String mobile, String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name" , name);
        contentValues.put("email" , email);

        Cursor cursor = db.rawQuery("select * from FriendsData where mobile= ?", new String[]{mobile} );
        if(cursor.getCount() > 0){
            long result = db.update("FriendsData", contentValues, "mobile=?" ,  new String[]{mobile});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public boolean deleteUserData(String mobile){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from FriendsData where mobile= ?", new String[]{mobile} );

        if(cursor.getCount() > 0){
            long result = db.delete("FriendsData",  "mobile=?" ,  new String[]{mobile});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getUserData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from FriendsData",null );
        return  cursor;

    }

}
