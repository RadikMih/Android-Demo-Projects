package com.englishstories

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        val itemIndex = intent.extras?.get("item_index").toString().toInt()
        Toast.makeText(this, itemIndex.toString(), Toast.LENGTH_LONG).show()

        title = Data.stories[itemIndex].title
        textViewStoryFullText.text = Data.stories[itemIndex].fullText
    }
}
