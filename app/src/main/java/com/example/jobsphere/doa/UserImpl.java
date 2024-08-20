package com.example.jobsphere.doa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserImpl extends SQLiteOpenHelper {

    private final Context context;

    public UserImpl(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Register User Function


    //

    //Email Exists Function


    //

    //Login Function


    //

    //Current userID function



    //

    //Get user details function


    //

    //Update user details


    //

    private void closeCursor(Cursor cursor) {
        if (cursor!=null) {
            cursor.close();
        }
    }
}
