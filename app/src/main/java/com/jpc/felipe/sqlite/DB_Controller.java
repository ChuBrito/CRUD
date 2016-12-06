package com.jpc.felipe.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by Issao on 27/11/2016.
 */
public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "GAME.db", factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE GAME_USER( ID INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT UNIQUE, GAME TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IS EXISTS GAME_USER;");
        onCreate(sqLiteDatabase);
    }
    public void insert_users(String user, String game){
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER", user);
        contentValues.put("GAME", game);
        this.getWritableDatabase().insertOrThrow("GAME_USER", "", contentValues);
    }
    public void delete_users(String user){
        this.getWritableDatabase().delete("GAME_USER", "USER='" + user + "'", null);
    }
    public  void update_users(String old_users, String new_users){
        this.getWritableDatabase().execSQL("UPDATE GAME_USER SET USER='" + new_users +"' WHERE USER='"+ old_users +"'");
    }
    public void list_all_users(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM GAME_USER", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }
    }
}
