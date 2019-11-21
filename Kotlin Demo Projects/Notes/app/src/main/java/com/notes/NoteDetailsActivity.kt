package com.notes

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note_details.*

class NoteDetailsActivity : AppCompatActivity() {

    var db: SQLiteDatabase? = null
    var noteId: Int = 0
    var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        val notesDataBaseHelper = NotesSQLiteOpenHelper(this)
        db = notesDataBaseHelper.writableDatabase

        noteId = intent.extras!!.get("NOTE_ID").toString().toInt()
        if (noteId != 0) {
            cursor = db!!.query(
                "notes", arrayOf("title", "description"),
                "_id=?",
                arrayOf(noteId.toString()),
                null, null, null
            )

            if (cursor!!.moveToFirst()) {
                titleEditText.setText(cursor!!.getString(0))
                descriptionEditText.setText(cursor!!.getString(1))
            }
        }
    }

    private fun insertNote(newNote: ContentValues) {
        db!!.insert("notes", null, newNote)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Note saved!", Toast.LENGTH_LONG).show()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.save_note) {
            val newNote = ContentValues()
            if (titleEditText.text.isEmpty()) {
                newNote.put("title", "Untitled")
            } else {
                newNote.put("title", titleEditText.text.toString())
            }
            newNote.put("description", descriptionEditText.text.toString())

            if (noteId == 0) {
                insertNote(newNote)
            } else {
                updateNote(newNote)
            }

        } else if (item.itemId == R.id.delete_note) {
            deleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(this).create()
        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want to delete '${titleEditText.text}'?")
        builder.setButton(
            AlertDialog.BUTTON_POSITIVE,
            "Yes",
            dialogClickListener
        )

        builder.setButton(
            AlertDialog.BUTTON_NEGATIVE,
            "Cancel",
            dialogClickListener
        )
        builder.show()

    }

    private val dialogClickListener = DialogInterface.OnClickListener { _, which ->

        if (which == DialogInterface.BUTTON_POSITIVE) {
            db!!.delete("notes", "_id=?", arrayOf(noteId.toString()))
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Note deleted!", Toast.LENGTH_LONG).show()

        }
    }


    private fun updateNote(newNote: ContentValues) {
        db!!.update("notes", newNote, "_id=?", arrayOf(noteId.toString()))
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Note updated!", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        db!!.close()
    }
}
