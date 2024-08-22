package com.example.jobsphere.doa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserImpl extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jobsphere.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "userID"; // Changed from "id" to "userID"
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname"; // Added surname column
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_WORKER = "worker";

    private final Context context;

    public UserImpl(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY, " + // Changed to TEXT to match userID type
                COLUMN_NAME + " TEXT, " +
                COLUMN_SURNAME + " TEXT, " + // Added surname column
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_WORKER + " INTEGER)"; // Added worker column (boolean stored as INTEGER)
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    //Register User Function
    public boolean registerUser(String userID, String name, String surname, String email, String password, boolean worker)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, userID);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SURNAME, surname);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_WORKER, worker ? 1 : 0); // Store boolean as 1 or 0

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;  // returns true if insertion was successful
    }

    //

    //Email Exists Function
    public boolean emailExists(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        boolean exists = cursor.getCount() > 0;
        closeCursor(cursor);
        return exists;
    }

    //

    //Login Function
    public boolean login(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});

        boolean loginSuccess = cursor.getCount() > 0;
        closeCursor(cursor);
        return loginSuccess;
    }

    //

    //Current userID function
    public String getUserId(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        String userId = null;
        if (cursor.moveToFirst()) {
            userId = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
        }
        closeCursor(cursor);
        return userId;
    }


    //

    //Get user details function
    public Cursor getUserDetails(String userID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + " = ?";
        return db.rawQuery(query, new String[]{userID});
    }

    //

    //Update user details
    public boolean updateUserDetails(String userID, String name, String surname, String email, String password, boolean worker)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SURNAME, surname);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_WORKER, worker ? 1 : 0); // Store boolean as 1 or 0

        int rowsAffected = db.update(TABLE_USERS, values, COLUMN_ID + " = ?", new String[]{userID});
        return rowsAffected > 0;  // returns true if update was successful
    }

    //
    private void closeCursor(Cursor cursor) {
        if (cursor!=null) {
            cursor.close();
        }
    }
}
