package com.example.inquallity.notes.notesstorage.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alexandrova Olga on 16-May-17.
 */

public class NotesDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = NotesDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    public NotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ADDEDNOTE_TABLE = "CREATE TABLE " + NotesContract.NewNote.TABLE_NAME + " ("
                +NotesContract.NewNote._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NotesContract.NewNote.COLUMN_NOTETEXT +");";
        db.execSQL(SQL_CREATE_ADDEDNOTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
