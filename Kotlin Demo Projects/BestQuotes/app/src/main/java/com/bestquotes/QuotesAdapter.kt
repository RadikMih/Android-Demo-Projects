package com.bestquotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuotesAdapter(
    val context: Context,
    val quotes: List<String>,
    private val onItemClick: (String) -> Unit
) :
    RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quote_item, parent, false)
        return QuotesViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int {
        return quotes.count()
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.bindQuotes(quotes[position])
    }

    inner class QuotesViewHolder(itemView: View, onItemClick: (String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val quotesTextView = itemView.findViewById<TextView>(R.id.quoteTextView)

        fun bindQuotes(quote: String) {
            quotesTextView.text = quote
            itemView.setOnClickListener{
                onItemClick(quote)

            }
        }
    }


}