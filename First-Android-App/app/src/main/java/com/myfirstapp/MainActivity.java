package com.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = this.findViewById(R.id.text_view_title);
        textView.setText(R.string.products_title);

        final ArrayList<String> cars_list = new ArrayList<>();

        cars_list.add("Polo");
        cars_list.add("Lupo");
        cars_list.add("Golf");
        cars_list.add("UP!");
        cars_list.add("Passat");
        cars_list.add("Tiguan");
        cars_list.add("Touareg");
        cars_list.add("Multivan");
        cars_list.add("Caddy");
        cars_list.add("Eos");
        cars_list.add("Beetle");
        cars_list.add("Tiguan");
        cars_list.add("Touran");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item_cars,
                R.id.text_view_cars,
                cars_list
        );

        final ListView listView = this.findViewById(R.id.list_view_cars);
        listView.setAdapter(adapter);

        Context context = this;
        String text = "Hello VW";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast newToast = Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT);
//                newToast.show();
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                detailIntent.putExtra("model_name", cars_list.get(position));
                startActivity(detailIntent);


            }
        });

    }


}
