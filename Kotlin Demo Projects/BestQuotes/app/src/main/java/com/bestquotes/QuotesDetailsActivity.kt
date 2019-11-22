package com.bestquotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class QuotesDetailsActivity : AppCompatActivity() {

    var quotesCategoryId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes_details)

        quotesCategoryId = intent.extras!!.get("QUOTE_CATEGORY_ID").toString().toInt()
        Toast.makeText(this, quotesCategoryId.toString(), Toast.LENGTH_LONG).show()
    }
}
