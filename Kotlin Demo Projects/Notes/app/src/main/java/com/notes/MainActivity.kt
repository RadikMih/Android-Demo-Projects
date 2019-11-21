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
            openNoteDetailsActivity(0)
        }

        notesListView.setOnItemClickListener { parent, view, position, id ->
            openNoteDetailsActivity(id)
        }
    }

    fun openNoteDetailsActivity(noteId: Long) {
        val intent = Intent(this, NoteDetailsActivity::class.java)
        intent.putExtra("NOTE_ID", noteId)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()

        var objectToCreateDB = NotesSQLiteOpenHelper(this)
        db = objectToCreateDB.readableDatabase

        cursor = db!!.query(
            "notes", arrayOf("_id", "title"),
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
