package com.bestquotes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BestQuotesSQLiteOpenHelper(context: Context) :
    SQLiteOpenHelper(context, "BestQuotesDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE quote_categories(" +
                    "_id INTEGER PRIMARY KEY," +
                    "image_resource_id TEXT," +
                    "name TEXT" +
                    ")"
        )
        insertQuotesCategory(db, 1, R.drawable.motor5, "Category 1")
        insertQuotesCategory(db, 2, R.drawable.motor6, "Category 2")
        insertQuotesCategory(db, 3, R.drawable.motor8, "Category 3")
        insertQuotesCategory(db, 4, R.drawable.motor9, "Category 4")
        insertQuotesCategory(db, 5, R.drawable.motor4, "Category 5")
        insertQuotesCategory(db, 6, R.drawable.motor3, "Category 6")

        db.execSQL("CREATE TABLE quotes(quote TEXT, category_id INTEGER)")

        insertQuotes(db, "Text 1", 1)
        insertQuotes(db, "Text 2", 1)
        insertQuotes(db, "Test 3", 1)

    }

    private fun insertQuotes(db: SQLiteDatabase?, quote: String, categoryId: Int) {
        val contentValues = ContentValues()
        contentValues.put("quote", quote)
        contentValues.put("category_id", categoryId)

        db!!.insert("quotes", null, contentValues)
    }

    private fun insertQuotesCategory(db: SQLiteDatabase?, id: Int, resourceId: Int, name: String) {
        val contentValues = ContentValues()
        contentValues.put("_id", id)
        contentValues.put("image_resource_id", resourceId)
        contentValues.put("name", name)

        db!!.insert("quote_categories", null, contentValues)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}