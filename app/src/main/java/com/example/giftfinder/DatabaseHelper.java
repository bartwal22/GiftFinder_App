package com.example.giftfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "GiftFinderDB";
    private static final int DATABASE_VERSION = 1;
   private static final String TABLE_USERS = "users";

  // Columns of the users table
   private static final String COLUMN_ID = "id";
   private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
   private static final String COLUMN_EMAIL = "email";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // Create users table
       String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +  // Enforcing unique usernames
                COLUMN_PASSWORD + " TEXT, " +                COLUMN_EMAIL + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Method to check if the user exists
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to find user by username and password
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";

        Cursor cursor = null;
        boolean userExists = false;
        try {
            cursor = db.rawQuery(query, new String[]{username, password});
            userExists = cursor.getCount() > 0; // If cursor contains rows, user exists
        } catch (SQLException e) {
            e.printStackTrace();  // Log the error
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return userExists;
    }

    // Method to add user to the database (sign up)
    public boolean addUser(String username, String password, String email) {SQLiteDatabase db = this.getWritableDatabase();
        // Check if user already exists
        if (checkUser(username, password)) {
            return false; // User with this username already exists
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_EMAIL, email);  // Make sure this line matches the method signature

        try {
            long result = db.insert(TABLE_USERS, null, values);
            db.close();
            return result != -1; // If the insertion fails, it will return -1
        } catch (SQLException e) {
            e.printStackTrace(); // Log any errors
            db.close();
            return false;
        }
    }
}
