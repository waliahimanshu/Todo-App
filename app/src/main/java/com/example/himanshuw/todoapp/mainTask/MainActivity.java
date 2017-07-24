package com.example.himanshuw.todoapp.mainTask;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.himanshuw.todoapp.R;
import com.example.himanshuw.todoapp.data.DatabaseHelper;
import com.example.himanshuw.todoapp.data.TodosContract;
import com.example.himanshuw.todoapp.util.ActivityUtils;

import static com.example.himanshuw.todoapp.data.TodosContract.TodosEntry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintask_activity);
        createTodosDatabase();
        readData();


        createCategory();
        MainTaskFragment tasksFragment = (MainTaskFragment) getSupportFragmentManager().findFragmentById(R.id.mainTaskFragmentPlaceholder);
        if (tasksFragment == null) {
            // Create the fragment
            tasksFragment = MainTaskFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tasksFragment, R.id.mainTaskFragmentPlaceholder);
        }
    }

    private void createCategory() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodosContract.CategoriesEntry.COLUMN_DESCRIPTION, "work");

        Uri insert = getContentResolver().insert(TodosContract.CategoriesEntry.CONTENT_URI, contentValues);

        Log.d("MainAct", "insetedCategory");
    }

    private void createTodosDatabase() {
        //create a database!
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TodosEntry.COLUMN_TEXT, "Hello mr Walia!");
        contentValues.put(TodosEntry.COLUMN_CATEGORY, 1);
        contentValues.put(TodosEntry.COLUMN_CREATED, "12-08-1987");
        contentValues.put(TodosEntry.COLUMN_DONE, "0");
        database.insert(TodosEntry.TABLE_NAME, null, contentValues);
    }

    private void readData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase database = databaseHelper.getReadableDatabase();


        String[] projection = {
                TodosEntry.COLUMN_TEXT,
                TodosEntry.COLUMN_CREATED,
                TodosEntry.COLUMN_EXPIRED,
                TodosEntry.COLUMN_DONE,
                TodosEntry.COLUMN_CATEGORY,
        };

        String selection = TodosEntry.COLUMN_CATEGORY + " = ? ";
        String[] selectionArga = {"1"};

        Cursor cursor = database.query(TodosEntry.TABLE_NAME, projection, selection, selectionArga, null, null, null);

        //via content resolver
        Cursor query = getContentResolver().query(TodosEntry.CONTENT_URI, projection, selection, selectionArga, null);

//        cursor.moveToFirst();
//        cursor.moveToNext();
//        cursor.moveToLast();
//        cursor.moveToPrevious();
//        cursor.getColumnName()
//        cursor.getColumnNames()
        Log.i("Query", String.valueOf(cursor.getCount()));


//        database.update()

    }
}

