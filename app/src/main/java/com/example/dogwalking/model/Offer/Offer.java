package com.example.dogwalking.model.Offer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dogwalking.model.profile.ProfileEntry;

public class Offer extends SQLiteOpenHelper {

    private Context context;
    private static final String DBNAME = "DogWalking.db";
    private static final int VERSION = 1;

    public Offer(Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Offers (offerId int not null primary key, description TEXT, " +
                " username TEXT not null )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Offers");

    }

    public boolean insertOffer(int offerId, String description, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("offerId", offerId);
        cv.put("description", description);
        cv.put("username", username);
        long result = db.insert("Offers", "", cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
}
