package com.example.himanshuw.todoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.himanshuw.todoapp.data.TodosContract.CategoriesEntry;

import static com.example.himanshuw.todoapp.data.TodosContract.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todosapp.db";
    private static final int DATABASE_VERSION = 1;

    //SQL to create tables
    private static final String TABLE_CATEGORIES_CREATE =
            "CREATE TABLE " + CategoriesEntry.TABLE_NAME + " (" +
                    CategoriesEntry._ID + " INTEGER PRIMARY KEY, " +
                    CategoriesEntry.COLUMN_DESCRIPTION + " TEXT " +
                    ")";
    private static final String TABLE_TODOS_CREATE =
            "CREATE TABLE " + TodosEntry.TABLE_NAME + " (" +
                    TodosEntry._ID + " INTEGER PRIMARY KEY, " +
                    TodosEntry.COLUMN_TEXT + " TEXT, " +
                    TodosEntry.COLUMN_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    TodosEntry.COLUMN_EXPIRED + " TEXT, " +
                    TodosEntry.COLUMN_DONE + " INTEGER, " +
                    TodosEntry.COLUMN_CATEGORY + " INTEGER, " +
                    " FOREIGN KEY(" + TodosEntry.COLUMN_CATEGORY + ") REFERENCES " + CategoriesEntry.TABLE_NAME
                    + "(" + CategoriesEntry._ID + ") " + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CATEGORIES_CREATE);
        db.execSQL(TABLE_TODOS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TodosEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+CategoriesEntry.TABLE_NAME);
        onCreate(db);
    }
}
