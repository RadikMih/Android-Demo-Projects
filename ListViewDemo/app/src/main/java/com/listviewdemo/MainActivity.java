package com.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = findViewById(R.id.myListView);
//        List<String> names = new ArrayList<>();
//        names.add("Test 1");
//        names.add("Test 2");
//        names.add("Test 3");
//        names.add("Test 4");

        final List<String> names = new ArrayList<>(asList("Test 1", "Test 2", "Test 3", "Test 4"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // parent.setVisibility(View.GONE);
               //  the item that is tapped
               // position of the row
                String name = names.get(position);
                Log.i("Name: ", name);

                Toast.makeText(getApplicationContext(), "Name: " + name, Toast.LENGTH_LONG).show();
            }
        });



    }
}
