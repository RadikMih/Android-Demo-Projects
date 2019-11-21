package com.englishstories

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storyAdapter = ArrayAdapter<Story>(
            this,
            android.R.layout.simple_list_item_1,
            Data.stories
        )
        listView.adapter = storyAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            //Toast.makeText(this, stories[position], Toast.LENGTH_LONG).show()

            val intent = Intent(this, ScrollingActivity::class.java)
            intent.putExtra("item_index", position)

            startActivity(intent)



        }


    }
}
