package com.example.listdemoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashSet;

import static com.example.listdemoapp.MainActivity.notes;
import static com.example.listdemoapp.MainActivity.set;

public class EditNote extends AppCompatActivity implements TextWatcher {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText editText = findViewById(R.id.editText);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1){
            String fillerText = notes.get(noteId);
            editText.setText(fillerText);
        }

        editText.addTextChangedListener(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        notes.set(noteId, String.valueOf(charSequence));
        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences preferences = this.getSharedPreferences("com.example.listdemoapp", Context.MODE_PRIVATE);

        if (set == null) {
            set = new HashSet<>();
        } else {
            set.clear();
        }

        set.clear();
        set.addAll(notes);
        preferences.edit().remove("notes").apply();
        preferences.edit().putStringSet("notes", set).apply();


    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
