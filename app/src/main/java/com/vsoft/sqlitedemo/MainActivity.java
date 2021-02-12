package com.vsoft.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.vsoft.sqlitedemo.db.DBManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager dbManager = new DBManager(this);
        dbManager.open();
        dbManager.delete();
        dbManager.insert("Test1");
        dbManager.insert("Test2");
        dbManager.insert("Test3");
        dbManager.insert("Test4");
        dbManager.insert("Test5");
        dbManager.insert("Test6");
        dbManager.delete("Test4");
        dbManager.delete("Test5");

        List<String> messages = dbManager.fetch();
        for(String msg : messages)
            Log.d("msg", msg);
    }
}