package com.example.programminglknotebook.db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.programminglknotebook.dto.Note;

public final class DBHelper extends SQLiteOpenHelper {

    private static DBHelper db;

    public DBHelper( Context context) {
        super(context, "Notes.db", null, 1);
    }

    public static DBHelper getInstance(Activity activity){
        if(db == null){
            db =new DBHelper(activity.getApplicationContext());
            return db;
        }else{
            return db;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table userDetails(userNameID TEXT primary key , password TEXT , loggedStatus Integer)");
        db.execSQL("create Table notes_details(id Integer primary key ,header TEXT , content TEXT, userNameID TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists userDetails");
        db.execSQL("drop Table if exists notes_details");
    }

    public Boolean insert_UserData(String userName, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userNameID", userName);
        contentValues.put("password", password);

        long result = db.insert("userDetails", null,contentValues );
        if(result == -1){
            return  false;
        }else {
            return true;
        }

    }

    public Cursor getUserDataByName(String userName){
        SQLiteDatabase db =this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from userDetails where userNameID=?", new String[]{userName});
        return cursor;

    }


    public boolean insert_Note(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("header", note.getHeader());
        contentValues.put("content", note.getContent());
        contentValues.put("userNameID", note.getUserName());

        long result = db.insert("notes_details", null, contentValues );
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor get_Notes(String UserName) {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from notes_details where userNameID=?", new String[] {UserName});
        return cursor;
    }

    public Boolean update_Notes(Note  noteToEdit){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("header", noteToEdit.getHeader());
        contentValues.put("content", noteToEdit.getContent());

        long result = db.update("notes_details",contentValues, "id=?",  new String[]{Integer.toString(noteToEdit.getId())});
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean delete_Notes(Note  noteToDelete){
        SQLiteDatabase db= this.getWritableDatabase();

        long result = db.delete("notes_details", "id=?", new String[]{Integer.toString(noteToDelete.getId())});
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
