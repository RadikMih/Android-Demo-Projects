package com.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter adapter;

    public void viewPairedDevices(View view) {
        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        ListView pairedDevicesListView = findViewById(R.id.pairedDevicesListView);

        ArrayList pairedDevicesArrayList = new ArrayList();

        for (BluetoothDevice device : pairedDevices) {
            pairedDevicesArrayList.add(device.getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                pairedDevicesArrayList);
        pairedDevicesListView.setAdapter(arrayAdapter);
    }

    public void findDiscoverableDevices(View view) {
       Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
       startActivity(intent);
    }

    public void turnBluetoothOff(View view) {
        adapter.disable();

        if (adapter.isEnabled()) {
            Toast.makeText(
                    getApplicationContext(),
                    "Bluetooth could not be disabled",
                    Toast.LENGTH_LONG)
                    .show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Bluetooth tuned off",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (adapter.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Bluetooth is on", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);

            if (adapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Bluetooth turned on", Toast.LENGTH_LONG).show();
            }
        }
    }
}
