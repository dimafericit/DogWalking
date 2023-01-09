package com.example.dogwalking.model.profile;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dogwalking.ui.dashboard.Offer;

import java.util.ArrayList;

public class ProfileDbHelper extends SQLiteOpenHelper {

    private  Context context;
    private static final String DBNAME = "DogWalking.db";
    private static final int VERSION = 1;
    private static int id = 0;

    public ProfileDbHelper(Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Offers (offerId integer primary key autoincrement , description TEXT, " +
                " username TEXT not null, dogBreed TEXT, address TEXT )");

        sqLiteDatabase.execSQL("CREATE TABLE Messages (messageId integer primary key autoincrement ,"
                + " text TEXT, " +
                " sender TEXT , receiver TEXT, seen INTEGER )");

        sqLiteDatabase.execSQL("CREATE TABLE Contacts (contactId integer primary key autoincrement ,"
                + " recentMessage TEXT, " +
                " sender TEXT , receiver TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + ProfileEntry.TABLE_NAME + " (username TEXT not null primary key, password TEXT not null,"
                + ProfileEntry.COLUMN_EMAIL + " TEXT not null," + ProfileEntry.COLUMN_BIO + " TEXT," + ProfileEntry.COLUMN_IMAGE + " BLOB, phone TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Offers");

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

    public boolean insertOffer(String description, String username, String address, String dogBreed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("description", description);
        cv.put("username", username);
        cv.put("address", address);
        cv.put("dogBreed", dogBreed);
        long result = db.insert("Offers", "", cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean insertMessage(String text, String sender, String receiver, Integer seen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("text", text);
        cv.put("sender", sender);
        cv.put("receiver", receiver);
        cv.put("seen", seen);
        long result = db.insert("Messages", "", cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean insertContact(String recentMessage, String sender, String receiver) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("recentMessage", recentMessage);
        cv.put("sender", sender);
        cv.put("receiver", receiver);
        long result = db.insert("Contacts", "", cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public void updateImage(String username, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("image", image);
        String[] x = new String[1];
        x[0]=username;
        db.update("Users", cv, " username = ?", x);
    }

    public void updateBio(String username, String bio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bio", bio);
        String[] x = new String[1];
        x[0]=username;
        db.update("Users", cv, " username = ?", x);
    }

    public void updatePhone(String username, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("phone", phone);
        String[] x = new String[1];
        x[0]=username;
        db.update("Users", cv, " username = ?", x);
    }




    public void updateUsername(String username, String bio) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE users SET bio =  ? WHERE users.username = ?", new String[]{bio, username});
    }

    public byte[] getImage(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Users", null);
        if (c.moveToFirst()) {
            do {
                String user = c.getString(2);
                if (user.equals(username)){
                    byte[] image = c.getBlob(4);
                    return image;
                }
            } while (c.moveToNext());
        }
        return null;
    }

    public User getUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Users", null);
        if (c.moveToFirst()) {
            do {
                String userName = c.getString(0);
                if (userName.equals(username)){
                    String email = c.getString(2);
                    String phone = c.getString(5);
                    String bio = c.getString(3);
                    User user = new User(userName, bio, email, phone);
                    return user;
                }
            } while (c.moveToNext());
        }
        return null;
    }

    public Offer getOffer(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Offers", null);
        if (c.moveToFirst()) {
            do {
                String userName = c.getString(2);
                if (userName.equals(username)){
                    String description = c.getString(1);
                    String dog = c.getString(3);
                    String address = c.getString(4);
                    Offer offer = new Offer(description, dog, address, userName);
                    return offer;
                }
            } while (c.moveToNext());
        }
        return null;
    }

    public  ArrayList<Offer> offersList() {
        ArrayList<Offer> list = new ArrayList<>();
        String query = "SELECT * FROM Offers ";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String description = cursor.getString(1);
                String dogBreed = cursor.getString(3);
                String address = cursor.getString(4);
                String username = cursor.getString(2);

                Offer offer = new Offer(description, dogBreed, address, username);
                list.add(offer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public  ArrayList<Contact> contactsList(String username) {
        ArrayList<Contact> list = new ArrayList<>();
        String query = "SELECT * FROM Contacts  ";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String sender = cursor.getString(2);
                String receiver = cursor.getString(3);
                if (username.equals(sender) || username.equals(receiver)) {
                    String lastMessage = cursor.getString(1);

                    Contact contact = new Contact(sender, receiver, lastMessage);
                    list.add(contact);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public  ArrayList<Message> messagesList(String send, String get) {
        ArrayList<Message> list = new ArrayList<>();
        String query = "SELECT * FROM Messages ";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                String sender = cursor.getString(2);
                String receiver = cursor.getString(3);
                if (sender.equals(send) && receiver.equals(get) ){
                    String text = cursor.getString(1);
                    Integer seen = cursor.getInt(4);

                    Message message = new Message(sender, receiver,text,true, 0);
                    list.add(message);
                }
                else if ((sender.equals(get) && receiver.equals(send)) ){
                    String text = cursor.getString(1);
                    Integer seen = cursor.getInt(4);

                    Message message = new Message(sender, receiver,text,true, 1);
                    list.add(message);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void deleteOffer(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] x = new String[1];
        x[0]=username;
        int cursor = db.delete("Offers", "username = ?", x);
    }


}