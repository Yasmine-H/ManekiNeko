package com.ihm.androide.upmc.manekineko.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HAMDANI on 28/04/2018.
 */

public class UserDbHelper {

    private static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS USERDATA(_id integer primary key autoincrement, "
                    + "name text not null,"
                    + "password text not null,"
                    + "email text,"
                    + "photo text " // TODO FAVORITEMEALS
                    +");";

    private static final String DATABASE_NAME = "RESTAURANTDB";

    private static final String DATABASE_TABLE = "USERDATA";

    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    public UserDbHelper(Context ctx) {

        db = ctx.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        db.execSQL(DATABASE_CREATE);
    }

    public void close() {
        db.close();
    }

    public void createUser(User user){ //String name, String password, String email, String photo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("name", user.getName());
        initialValues.put("password", user.getPassword());
        initialValues.put("email", user.getEmail());
        initialValues.put("photo", user.getPhoto());
        db.insert(DATABASE_TABLE, null, initialValues);
    }

    public void deleteUser(long userId) {
        db.delete(DATABASE_TABLE, "_id=" + userId, null);
    }


    public ArrayList<User> fetchAllUsers() {
        ArrayList<User> userList = new ArrayList<User>();

        Cursor c =
                db.query(DATABASE_TABLE, new String[] {
                        "_id", "name", "password", "email", "photo"}, null, null, null, null, null);
        int numUsers = c.getCount();
        c.moveToFirst();
        for (int i = 0; i < numUsers; ++i) {
            long id = c.getLong(0);
            String name = c.getString(1);
            String password = c.getString(2);
            String email = c.getString(3);
            String photo = c.getString(4);
            userList.add(new User(id, name, password, email, photo, null)); //TODO
            c.moveToNext();
        }
        return userList;
    }

    public User fetchUser(long userId) {
        Cursor c =
                db.query(true, DATABASE_TABLE, new String[] {
                                "_id", "name", "password", "email", "photo"}, "_id=" + userId, null, null, null,
                        null, null);


        if (c.getCount() > 0) {
            c.moveToFirst();
            long id = c.getLong(0);
            String name = c.getString(1);
            String password = c.getString(2);
            String email = c.getString(3);
            String photo = c.getString(4);
            User user = new User(id, name, password, email, photo, null); //TODO
            return user;
        } else {
            return null;
        }
    }


    public String fetchUser(User user) {
        Cursor c =
                db.query(true, DATABASE_TABLE, new String[] {
                                "_id", "name", "password", "email", "photo"}, "name=\"" + user.getName()+"\"", null, null, null,
                        null, null);


        if (c.getCount() > 0) {
            String password = c.getString(2);
            return password;
        } else {
            return null;
        }
    }


    public void updateUser(User user) {
        ContentValues args = new ContentValues();
        args.put("name", user.getName());
        args.put("password", user.getPassword());
        args.put("email", user.getEmail());
        args.put("photo", user.getPhoto());
        db.update(DATABASE_TABLE, args, "_id=" + user.getId(), null);
    }

    public Cursor GetAllUsers() {
        return db.query(DATABASE_TABLE, new String[] {
                "_id", "name", "password", "email", "photo"}, null, null, null, null, null);

    }

}
