package com.example.jobsphere.doa;

import static com.example.jobsphere.utility.DBStrings.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.jobsphere.model.User;

public class UserImpl extends SQLiteOpenHelper {

    private final Context context;

    public UserImpl(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn","Creating the database");
        db.execSQL(CREATE_USER_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Warn","Updating the Database");
        db.execSQL(DROP_USER_TABLE_QUERY);
        onCreate(db);

    }

    //Register User Function
    public boolean register(User user) {
        SQLiteDatabase jobsphereDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean valid = false;

        contentValues.put(COLUMN_USER_FIRST_NAME, user.getName());
        contentValues.put(COLUMN_USER_LAST_NAME, user.getSurname());
        contentValues.put(COLUMN_USER_EMAIL_ADDRESS, user.getEmail());
        contentValues.put(COLUMN_USER_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_USER_WORKER,user.isWorker());

        //Check if email exists
        if(isEmailExists(user.getEmail())) {
            Toast.makeText(context.getApplicationContext(), "User already exists with this Email Address.", Toast.LENGTH_SHORT).show();
        } else {
            jobsphereDB.insert(USER_TABLE, null, contentValues);
            valid = true;
            Toast.makeText(context.getApplicationContext(), "Your account was created successfully", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }


    //

//Email Exists Function
private Boolean isEmailExists(String email) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(USER_TABLE,// Selecting Table
            new String[]{COLUMN_USER_EMAIL_ADDRESS}, //Selecting columns want to query
            COLUMN_USER_EMAIL_ADDRESS + " = ?",
            new String[]{email},//Where clause
            null, null, null);
    if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
        return true;
    }
    closeCursor(cursor);
    return false;
}


//



    //Login Function
    public boolean login(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean valid = false;

        String password = user.getPassword();
        Cursor cursor = db.query(USER_TABLE,// Selecting Table
                new String[]{COLUMN_USER_ID, COLUMN_USER_FIRST_NAME, COLUMN_USER_LAST_NAME, COLUMN_USER_EMAIL_ADDRESS, COLUMN_USER_PASSWORD,COLUMN_USER_WORKER},//Selecting columns want to query
                COLUMN_USER_EMAIL_ADDRESS + " = ? ",
                new String[]{user.getEmail()},//Where clause
                null, null, null);


        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            System.out.println("user does exist..");
            User user1 = new User(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),Boolean.parseBoolean(cursor.getString(5)));

            if(user.getPassword().equals(user1.getPassword())) {
                valid = true;
            }
        }


        db.close();
        closeCursor(cursor);
        return valid;
    }
    //

    //Current userID function
    public long getCurrentUserId(String emailAddress) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USER_TABLE,// Selecting Table
                new String[]{COLUMN_USER_ID},//Selecting columns want to query
                COLUMN_USER_EMAIL_ADDRESS + " = ?",
                new String[]{String.valueOf(emailAddress)},//Where clause
                null, null, null);

        long userId = -999;

        while(cursor.moveToNext()) {
            userId = cursor.getLong(0);
        }

        closeCursor(cursor);
        return userId;
    }


    //Get user details function
    public User getUserDetails(String theEmailAddress) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        Cursor cursor = db.query(USER_TABLE,// Selecting Table
                new String[]{COLUMN_USER_ID, COLUMN_USER_FIRST_NAME, COLUMN_USER_LAST_NAME, COLUMN_USER_EMAIL_ADDRESS, COLUMN_USER_PASSWORD,COLUMN_USER_WORKER},//Selecting columns want to query
                COLUMN_USER_EMAIL_ADDRESS + " = ? ",
                new String[]{String.valueOf(theEmailAddress)},//Where clause
                null, null, null);

        while(cursor.moveToNext()) {
            String userID = cursor.getString(0);
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            String emailAddress = cursor.getString(3);
            String password = cursor.getString(4);
            boolean worker = Boolean.parseBoolean(cursor.getString(5));

            user = new User(userID,firstName, lastName, emailAddress, password,worker);
        }

        closeCursor(cursor);
        return user;
    }

    //

    //Update user details
    public boolean updateUser(User user) {
        long result = 0;
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_FIRST_NAME, user.getName());
        contentValues.put(COLUMN_USER_LAST_NAME,user.getSurname());
        contentValues.put(COLUMN_USER_EMAIL_ADDRESS, user.getEmail());
        contentValues.put(COLUMN_USER_PASSWORD,user.getPassword());

        Cursor cursor = DB.rawQuery("Select * from " + USER_TABLE + " where " + COLUMN_USER_ID  + " = ?", new String[]{String.valueOf(user.getUserID())});
        if (cursor.getCount() > 0) {
            result = DB.update(USER_TABLE, contentValues, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(user.getUserID())});
        }
        closeCursor(cursor);
        DB.close();

        return result != -1;
    }


    //

    private void closeCursor(Cursor cursor) {
        if (cursor!=null) {
            cursor.close();
        }
    }
}
