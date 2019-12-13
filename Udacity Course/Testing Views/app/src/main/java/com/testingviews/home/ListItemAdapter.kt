package com.testingviews.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.testingviews.R
import kotlinx.android.synthetic.main.layout_content_list_item.view.*

class ListItemAdapter(var items: List<Data>) :
    RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ListItemViewHolder(inflater, parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val data: Data = items[position]
        holder.bind(data)
    }

    fun submitList(list: List<Data>) {
        items = list as ArrayList<Data>
    }

    class ListItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_content_list_item, parent, false)) {
        val name: TextView = itemView.tv_station_name
        val image: ImageView = itemView.iv_station_logo

        fun bind(data: Data) {
            name.text = data.title
            image.setImageResource(data.image)
        }
    }
}