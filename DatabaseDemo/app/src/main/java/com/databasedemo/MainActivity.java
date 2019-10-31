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
         //   database.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
         //   database.execSQL("INSERT INTO users (name, age) VALUES ('Test3', 33)");
         //   database.execSQL("INSERT INTO users (name, age) VALUES ('Test4', 34)");

         // database.execSQL("DELETE FROM users WHERE name = 'Test4'");
         // database.execSQL("update users set age = 34 where name = 'Test3'");

            // WHERE age > 31 AND name LIKE '%4'

            Cursor c = database.rawQuery("SELECT * FROM users", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();
            while (c != null) {
                Log.i("User Result - name", c.getString(nameIndex));
                Log.i("User Result - age", Integer.toString(c.getInt(ageIndex)));
                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
