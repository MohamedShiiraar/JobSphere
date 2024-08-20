package com.example.jobsphere.doa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class JobImpl extends SQLiteOpenHelper {
    private final Context context;

    public JobImpl(@Nullable Context context) {
        super();
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Add job function


    //

    //Delete a job function


    //

    //Get all the jobs


    //


    //Get a job by ID


    //


    //Get all jobs


    //

    private void closeCursor(Cursor cursor) {
        if (cursor!=null) {
            cursor.close();
        }
    }



}
