package com.example.dogwalking.model.profile;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

public class ProfileDbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DBNAME = "DogWalking.db";
    private static final int VERSION = 1;

    public ProfileDbHelper(Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ProfileEntry.TABLE_NAME + " (username TEXT not null primary key, password TEXT not null,"
                + ProfileEntry.COLUMN_EMAIL + " TEXT not null," + ProfileEntry.COLUMN_BIO + " TEXT," + ProfileEntry.COLUMN_IMAGE + " BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");

    }

    public boolean insertUser(String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("password", password);
        cv.put("email", email);
        cv.put("image", " ");
        cv.put("bio", " ");
        long result = db.insert("Users", "", cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean usernameExists(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users where username = ?",
                new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        return false;
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE " +
                "username=? AND password=?", new String[]{username, password});

        if (cursor.getCount() == 1)
            return true;
        return false;
    }

    public void updateImage(String username, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE users SET image =  ? WHERE users.username = ?", new String[]{String.valueOf(image), username});
    }

    public void updateBio(String username, String newUsername) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE users SET bio =  ? WHERE users.username = ?", new String[]{username, newUsername});
    }
    public void updateUsername(String username, String bio) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE users SET bio =  ? WHERE users.username = ?", new String[]{bio, username});
    }

}