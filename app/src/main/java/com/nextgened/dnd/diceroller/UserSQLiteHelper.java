package com.nextgened.dnd.diceroller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 8/25/2016.
 */
public class UserSQLiteHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DiceRoller.db";

    private static final String CREATE_USER_v1 =
            "CREATE TABLE User ("
            + "customerId INTEGER PRIMARY KEY,"
            + "firstName VARCHAR(100),"
            + "lastName VARCHAR(100),"
            + "phoneNumber VARCHAR(20),"
            + "email VARCHAR(100),"
            + "birthDate DATE,"
            + "createdDate DATE,"
            + "lastUpdated DATE"
            + ")"
            ;

    public UserSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_v1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
