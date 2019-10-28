package com.timestables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView timeTableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timeTableSeekBar = findViewById(R.id.timesTableSeekBar);
        timeTableListView = findViewById(R.id.timesTableListView);

        timeTableSeekBar.setMax(20);
        timeTableSeekBar.setProgress(10);

        timeTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesTable;
                if (progress < 1) {
                    timesTable = min;
                    timeTableSeekBar.setProgress(min);
                } else {
                    timesTable = progress;
                }

                Log.i("SeekBar value", Integer.toString(timesTable));

                generateTimesTable(timesTable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

     //   generateTimesTable(10);
    }

    public void generateTimesTable(int timesTable) {
        List<String> content = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            content.add(i * timesTable + "");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, content);
        timeTableListView.setAdapter(adapter);
    }
}
