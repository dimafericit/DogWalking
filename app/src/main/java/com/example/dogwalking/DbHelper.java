package com.example.dogwalking;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DBNAME = "DogWalking.db";
    private static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Users(username TEXT not null primary key, password TEXT not null, " +
                "email TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");

    }

    public boolean insertUser(String username, String password, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("password", password);
        cv.put("email", email);
        long result = db.insert("Users", null, cv);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }


    }

    public boolean usernameExists(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users where username = ?",
                new String[]{username});
        if(cursor.getCount()>0)
            return true;
        return false;
    }

    public boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE " +
                "username=? AND password=?", new String[]{username, password});

        if(cursor.getCount()==1)
            return true;
        return false;
    }

}