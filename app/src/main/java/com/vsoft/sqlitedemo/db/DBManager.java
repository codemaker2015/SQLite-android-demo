package com.vsoft.sqlitedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void delete() {
        dbHelper.onUpgrade(database, 1,2);
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String msg) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.COLUMN_NAME_MESSAGE, msg);
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public List<String> fetch() {
        String[] columns = new String[] { DBHelper.COLUMN_NAME_ID, DBHelper.COLUMN_NAME_MESSAGE };
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);

        List<String> messages = new ArrayList<String>();
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                String msg = cursor.getString(
                        cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME_MESSAGE));
                messages.add(msg);
            } while(cursor.moveToNext());
            cursor.close();
        }

        return messages;
    }

    public int update(long id, String msg) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COLUMN_NAME_MESSAGE, msg);
        int i = database.update(DBHelper.TABLE_NAME, contentValues, DBHelper.COLUMN_NAME_ID + " = " + id, null);
        return i;
    }

    public void delete(String msg) {
        database.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_NAME_MESSAGE + "= '" + msg + "'", null);
    }
}
