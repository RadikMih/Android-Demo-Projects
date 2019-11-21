package com.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class NoteDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_details_menu, menu)


        return super.onCreateOptionsMenu(menu)
    }
}
