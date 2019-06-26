

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
    private static final String KEY_JOB = "job";
    private static final String KEY_AGE = "age";
    private static final String KEY_GENDER = "gender";

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
                + KEY_JOB + " TEXT, "
                + KEY_AGE + " INTEGER, "
                + KEY_GENDER + " BOOLEAN" + ")";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }


    void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_PROFILE_PIC, user.getProfilePic());
        values.put(KEY_JOB, user.getJob());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_GENDER, user.getGender());

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
            user.setJob(cursor.getString(4));
            user.setAge(Integer.parseInt(cursor.getString(5)));
            user.setGender(cursor.getInt(5) > 0);

            userList.add(user);
        } while (cursor.moveToNext());

        db.close();
        return userList;
    }

    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_PROFILE_PIC, user.getProfilePic());
        values.put(KEY_JOB, user.getJob());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_GENDER, user.getGender());

        db.update(TABLE_USERS, values, KEY_ID + "="
                + String.valueOf(user.getId()), null);
        db.close();
    }

    public void updateUserById(User user, int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_PROFILE_PIC, user.getProfilePic());
        values.put(KEY_JOB, user.getJob());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_GENDER, user.getGender());

        db.update(TABLE_USERS, values, KEY_ID + "="
                + String.valueOf(id), null);
        db.close();
    }

    public void deleteUserById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + "=?",
                new String[]{String.valueOf(id)});
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

    int getIdByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT " + KEY_ID + " FROM "
                + TABLE_USERS + " WHERE "
                + KEY_USERNAME + " = \""
                + username + "\"";
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToNext();
        try{
            int ret = cursor.getInt(0);
            cursor.close();
            db.close();
            return ret;
        }catch (IndexOutOfBoundsException e){
            cursor.close();
            db.close();
            return -1 ;
        }
    }

    public User getUserById (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + TABLE_USERS
                + " WHERE " + KEY_ID
                + " = " + id;
        Cursor cursor = db.rawQuery(countQuery,null);
        User user = new User();

        cursor.moveToNext();

        user.setId(Integer.parseInt(cursor.getString(0)));
        user.setUsername(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setProfilePic(cursor.getString(3));
        user.setJob(cursor.getString(4));
        user.setAge(Integer.parseInt(cursor.getString(5)));
        user.setGender(cursor.getInt(6) > 0);


        db.close();
        cursor.close();
        return user;

    }
    String getUsernameById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT " + KEY_USERNAME
                + " FROM " + TABLE_USERS
                + " WHERE " + KEY_ID
                + " = " + id;
        Cursor cursor = db.rawQuery(countQuery,null);

        cursor.moveToNext();
        return cursor.getString(0);
    }
    String getPasswordById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT " + KEY_USERNAME
                + " FROM " + TABLE_USERS
                + " WHERE " + KEY_ID
                + " = " + id;
        Cursor cursor = db.rawQuery(countQuery,null);

        cursor.moveToNext();
        return cursor.getString(2);
    }
    Boolean getGenderById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT " + KEY_USERNAME
                + " FROM " + TABLE_USERS
                + " WHERE " + KEY_ID
                + " = " + id;
        Cursor cursor = db.rawQuery(countQuery,null);

        cursor.moveToNext();

        boolean ret = cursor.getInt(6) > 0;
        db.close();
        cursor.close();
        return ret;
    }
    int getAgeById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT " + KEY_USERNAME
                + " FROM " + TABLE_USERS
                + " WHERE " + KEY_ID
                + " = " + id;
        Cursor cursor = db.rawQuery(countQuery,null);

        cursor.moveToNext();

        int ret = cursor.getInt(5);
        db.close();
        cursor.close();
        return ret;
    }
    String getJobById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT " + KEY_USERNAME
                + " FROM " + TABLE_USERS
                + " WHERE " + KEY_ID
                + " = " + id;
        Cursor cursor = db.rawQuery(countQuery,null);

        cursor.moveToNext();

        String ret = cursor.getString(4);
        db.close();
        cursor.close();
        return ret;
    }
    String getProfilePicById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT " + KEY_USERNAME
                + " FROM " + TABLE_USERS
                + " WHERE " + KEY_ID
                + " = " + id;
        Cursor cursor = db.rawQuery(countQuery,null);

        cursor.moveToNext();

        String ret = cursor.getString(3);
        db.close();
        cursor.close();
        return ret;
    }

    void setProfilePicById(int id, String profilePic){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROFILE_PIC, profilePic);

        db.update(TABLE_USERS, values, KEY_ID + "="
                + String.valueOf(id), null);
        db.close();
    }

}
