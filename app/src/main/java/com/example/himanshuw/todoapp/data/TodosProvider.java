package com.example.himanshuw.todoapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.himanshuw.todoapp.data.TodosContract.CONTENT_AUTHORITY;
import static com.example.himanshuw.todoapp.data.TodosContract.PATH_CATEGORIES;
import static com.example.himanshuw.todoapp.data.TodosContract.PATH_TODOS;

public class TodosProvider extends ContentProvider {

    //identifiers for the data to be retrieved

    private static final int TODOS = 1;
    private static final int TODOS_ID = 2;
    private static final int CATEGORIES = 3;
    private static final int CATEGORIES_ID = 4;

    // uri matcher

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {
        //uri to retrieve todos table
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_TODOS, TODOS);
        //uri to retrieve row in todos table
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_TODOS + "/#", TODOS_ID);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CATEGORIES, CATEGORIES);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CATEGORIES + "/#", CATEGORIES_ID);
    }

    private DatabaseHelper databaseHelper;

    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor;
        int match = uriMatcher.match(uri);

        String inTables = TodosContract.TodosEntry.TABLE_NAME
                + " inner join "
                + TodosContract.CategoriesEntry.TABLE_NAME
                + " on " + TodosContract.TodosEntry.COLUMN_CATEGORY + " = "
                + TodosContract.CategoriesEntry.TABLE_NAME + "." + TodosContract.CategoriesEntry._ID;
        SQLiteQueryBuilder builder;

        switch (match) {
            case TODOS:

                builder = new SQLiteQueryBuilder();
                builder.setTables(inTables);
                cursor = builder.query(database, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case TODOS_ID:
                builder = new SQLiteQueryBuilder();
                builder.setTables(inTables);
                selection = TodosContract.TodosEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = builder.query(database, projection, selection, selectionArgs, null, null, sortOrder);

                break;

            case CATEGORIES:

                cursor = database.query(TodosContract.CategoriesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case CATEGORIES_ID:

                selection = TodosContract.CategoriesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(TodosContract.CategoriesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Query unknown URI: " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
