package com.testingviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListItemAdapter(val items: ArrayList<String>) :
    RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {

        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_content_list_item, parent, false)

        return ListItemViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.title.text = items[position]
    }


    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_station_name)
        val image: ImageView = itemView.findViewById(R.id.iv_station_logo)
    }
}