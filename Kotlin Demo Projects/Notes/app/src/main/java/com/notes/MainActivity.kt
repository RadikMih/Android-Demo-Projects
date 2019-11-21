package com.notes

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var db: SQLiteDatabase? = null
    var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNoteFab.setOnClickListener {
            val intent = Intent(this, NoteDetailsActivity::class.java)
            startActivity(intent)
        }

        var objectToCreateDB = NotesSQLiteOpenHelper(this)
        db = objectToCreateDB.readableDatabase

        cursor = db!!.query("notes", arrayOf("_id", "title"),
            null, null, null, null, null
        )

        val listAdapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_1,
            cursor,
            arrayOf("title"),
            intArrayOf(android.R.id.text1),
            0
        )

        notesListView.adapter = listAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        cursor!!.close()
        db!!.close()
    }
}
