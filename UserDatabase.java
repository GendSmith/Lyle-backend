package com.example.yousef.server1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Yousef on 6/23/2019.
 */

public class UserDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UsersDatabase";
    private static final String TABLE_USERS = "Users";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PROFILE_PIC = "profilePic";
    private static final String KEY_Job = "job";
    private static final String KEY_AGE = "age";
    private static final String KEY_gender = "gender";

    public UserDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_USERNAME + " TEXT, "
                + KEY_PASSWORD + " TEXT, "
                + KEY_PROFILE_PIC + " TEXT, "
                + KEY_Job + " TEXT, "
                + KEY_AGE + " INTEGER, "
                + KEY_gender + " BOOLEAN" + ")";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }


    // adding a new user
    void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_PROFILE_PIC, user.getProfilePic());
        values.put(KEY_Job, user.getjob());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_gender, user.getgender());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }


    //to show all the users in an array list
    public ArrayList<User> getAllUsers(){
        ArrayList<User> userList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        do {
            User user = new User();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setProfilePic(cursor.getString(3));
            user.setjob(cursor.getString(4));
            user.setAge(Integer.parseInt(cursor.getString(5)));
            user.setgender(cursor.getInt(5) > 0);

            userList.add(user);
        } while (cursor.moveToNext());

        db.close();
        return userList;
    }


    // to update user info
    public int updateUsers(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_PROFILE_PIC, user.getProfilePic());
        values.put(KEY_Job, user.getjob());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_gender, user.getgender());

        return db.update(TABLE_USERS, values, KEY_ID + "=?",
                new String[]{String.valueOf(user.getId())});
    }


    //delete a user
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + "=?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public int getUserCount(){
        String countQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+ TABLE_USERS;
        db.execSQL(clearDBQuery);
    }

    public User getUserById (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(countQuery, null);
        User user = new User();

        cursor.moveToPosition(id);

        user.setId(Integer.parseInt(cursor.getString(0)));
        user.setUsername(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setProfilePic(cursor.getString(3));
        user.setjob(cursor.getString(4));
        user.setAge(Integer.parseInt(cursor.getString(5)));
        user.setgender(cursor.getInt(6) > 0);


        db.close();
        cursor.close();
        return user;

    }



    int getIdByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(countQuery, null);

        cursor.moveToFirst();
        do {
            if(cursor.getString(1).equals(username)){
                int temp = Integer.parseInt(cursor.getString(0));
                db.close();
                cursor.close();
                return temp;
            }

        } while (cursor.moveToNext());
        db.close();
        cursor.close();
        return -1;
    }

    String getUsernameById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(countQuery, null);
        User user = new User();

        cursor.moveToPosition(id);

        String ret = cursor.getString(1);
        db.close();
        cursor.close();
        return ret;
    }


    String getPasswordById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(countQuery, null);
        User user = new User();

        cursor.moveToPosition(id);

        String ret = cursor.getString(2);
        db.close();
        cursor.close();
        return ret;
    }

    
    Boolean getGenderById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(countQuery, null);
        User user = new User();

        cursor.moveToPosition(id);

        boolean ret = cursor.getInt(6) > 0;
        db.close();
        cursor.close();
        return ret;
    }


    int getAgeById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(countQuery, null);
        User user = new User();

        cursor.moveToPosition(id);

        int ret = Integer.parseInt(cursor.getString(5));
        db.close();
        cursor.close();
        return ret;
    }


    String getJobById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(countQuery, null);
        User user = new User();

        cursor.moveToPosition(id);

        String ret = cursor.getString(4);
        db.close();
        cursor.close();
        return ret;
    }

}
