package com.example.jobsphere.utility;

public class DBStrings {
    //Integrated Database Below
    public static final String DB_USERS = "users";
    public static final String DB_JOBS = "jobs";

    public static final String DATABASE_NAME = "jobsphere.db";
    public static final int DATABASE_VERSION = 1;

    public static final String USER_TABLE = "user";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_FIRST_NAME = "first_name";
    public static final String COLUMN_USER_LAST_NAME = "last_name";
    public static final String COLUMN_USER_EMAIL_ADDRESS = "email_address";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_WORKER = "Worker";

    public static final String JOBS_TABLE = "job";
    public static final String COLUMN_JOBS_ID = "job_id";
    public static final String COLUMN_JOBS_TITLE = "title";
    public static final String COLUMN_JOBS_DESCRIPTION = "description";
    public static final String COLUMN_JOBS_RATE_PER_HOUR = "rate";
    public static final String COLUMN_JOBS_AREA_LOCATED = "location";

    public static final String COLUMN_USER_JOBS_FK = "user_id";

    public static final String CREATE_USER_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_FIRST_NAME + " TEXT,"
            + COLUMN_USER_LAST_NAME + " TEXT," + COLUMN_USER_EMAIL_ADDRESS + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + COLUMN_USER_WORKER + " TEXT"+ ")";

    public static final String CREATE_JOBS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + JOBS_TABLE + "(" + COLUMN_JOBS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_JOBS_TITLE + " TEXT,"
            +COLUMN_JOBS_DESCRIPTION + " TEXT,"+ COLUMN_JOBS_RATE_PER_HOUR + " TEXT,"+ COLUMN_JOBS_AREA_LOCATED + " TEXT," + COLUMN_USER_JOBS_FK + " INTEGER,"
            + "FOREIGN KEY(" + COLUMN_USER_JOBS_FK + ") REFERENCES " + USER_TABLE + "(" + COLUMN_USER_ID + ") ON DELETE CASCADE ON UPDATE CASCADE)";

    public static final String DROP_USER_TABLE_QUERY = "DROP TABLE IF EXISTS " + USER_TABLE;
    public static final String DROP_JOB_TABLE_QUERY = "DROP TABLE IF EXISTS " + JOBS_TABLE;
}
