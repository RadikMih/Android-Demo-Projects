package com.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            database.execSQL("INSERT INTO users (name, age) VALUES ('Test1', 30)");
            database.execSQL("INSERT INTO users (name, age) VALUES ('Test2', 32)");

            Cursor c = database.rawQuery("SELECT * FROM users", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();
            while (c != null) {
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));
                c.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
