package com.example.jobsphere.doa;


import static com.example.jobsphere.utility.DBStrings.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.jobsphere.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobImpl extends SQLiteOpenHelper {
    private final Context context;

    public JobImpl(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn","Creating Database");
        db.execSQL(CREATE_JOBS_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Warn","Updating the database");
        db.execSQL(DROP_JOB_TABLE_QUERY);
        onCreate(db);

    }

    // Add job function
    public boolean addJob(Job jobs, String emailAddress) {
        SQLiteDatabase dbRead = this.getReadableDatabase();
        SQLiteDatabase dbWrite = this.getWritableDatabase();
        long result = 0;
        long userId = -1;

        Cursor cursor = dbRead.rawQuery("SELECT " + COLUMN_USER_ID + " FROM " + USER_TABLE + " WHERE " + COLUMN_USER_EMAIL_ADDRESS + " = ?", new String[]{emailAddress});

        if (cursor.moveToNext()) {
            userId = cursor.getLong(0);
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_JOBS_TITLE, jobs.getTitle());
        contentValues.put(COLUMN_JOBS_DESCRIPTION, jobs.getDescription());
        contentValues.put(COLUMN_JOBS_RATE_PER_HOUR, jobs.getSalary());
        contentValues.put(COLUMN_JOBS_AREA_LOCATED, jobs.getLocation());
        contentValues.put(COLUMN_USER_JOBS_FK, String.valueOf(userId));

        result = dbWrite.insert(JOBS_TABLE, null, contentValues);

        closeCursor(cursor);
        dbRead.close();
        dbWrite.close();
        return result != -1;
    }

    //Delete a job
    public Boolean deleteJob (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = 0;

        Cursor cursor = db.rawQuery("SELECT * FROM " + JOBS_TABLE + " WHERE " + COLUMN_JOBS_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.getCount() > 0) {
            result = db.delete(JOBS_TABLE, COLUMN_JOBS_ID + " = ?", new String[]{String.valueOf(id)});
        }

        closeCursor(cursor);
        db.close();
        return result != -1;
    }

    //Get all jobs
    public List<Job> getUserJobs (String userId) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Job> JobsList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + JOBS_TABLE + " WHERE " + COLUMN_USER_JOBS_FK + " = ?", new String[]{String.valueOf(userId)});
        // Now you can safely read data from the cursor
        readDataFromCursor(JobsList, cursor);
        return JobsList;
    }

    //Get job ID
    public List<Job> getJobsById (String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Job> JobsList2 = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + JOBS_TABLE + " WHERE " + COLUMN_JOBS_ID + " = ?", new String[]{String.valueOf(id)});
        readDataFromCursor(JobsList2, cursor);
        return JobsList2;
    }

    private void readDataFromCursor(List<Job> jobsList, Cursor cursor) {
        int count = 0;
        while(cursor.moveToNext()) {
            String jobsId = cursor.getString(0);
            String jobtitle = cursor.getString(1);
            String jobDescription = cursor.getString(2);
            double rateperhour = cursor.getDouble(3);
            String areaLocated = cursor.getString(4);
            String userID = cursor.getString(5);

            System.out.println(cursor.getString(count));

            Job jobs = new Job(jobsId, jobtitle,jobDescription, rateperhour, areaLocated, userID);

            count++;
            jobsList.add(jobs);
        }
        closeCursor(cursor);
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
}
