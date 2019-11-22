package com.bestquotes

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_quotes_details.*

class QuotesDetailsActivity : AppCompatActivity() {

    var quotesCategoryId = 0
    var db: SQLiteDatabase? = null
    var cursor: Cursor? = null
    var quotesAdapter: QuotesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes_details)

        quotesCategoryId = intent.extras!!.get("QUOTE_CATEGORY_ID").toString().toInt()
        //Toast.makeText(this, quotesCategoryId.toString(), Toast.LENGTH_LONG).show()

        val bestQuotesSQLiteOpenHelper = BestQuotesSQLiteOpenHelper(this)
        db = bestQuotesSQLiteOpenHelper.readableDatabase

        cursor = db!!.query(
            "quotes", arrayOf("quote"), "category_id=?",
            arrayOf(quotesCategoryId.toString()), null, null, null
        )

        var listOfQuotes = mutableListOf<String>()

        while (cursor!!.moveToNext()) {
            val quote = cursor!!.getString(0)
            listOfQuotes.add(quote)
        }

        quotesAdapter = QuotesAdapter(this, listOfQuotes){quote ->
            val sharedIntent = Intent()
            sharedIntent.action = Intent.ACTION_SEND
            sharedIntent.putExtra(Intent.EXTRA_TEXT, quote)
            sharedIntent.type = "text/plain"
            startActivity(sharedIntent)
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerViewQuotes.layoutManager = layoutManager
        recyclerViewQuotes.adapter = quotesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()

        db!!.close()
        cursor!!.close()

    }
}
