package com.testingviews.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.testingviews.R
import com.testingviews.home.Data
import kotlinx.android.synthetic.main.layout_content_list_item.view.*

class DiscoverAdapter(private var items: List<Data>) :
    RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return DiscoverViewHolder(inflater, parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        val data: Data = items[position]
        holder.bind(data)
    }

    fun submitList(list: List<Data>) {
        items = list as ArrayList<Data>
    }

    class DiscoverViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(
            inflater.inflate(
                R.layout.layout_discover_single_item,
                parent,
                false
            )
        ) {
        val name: TextView = itemView.tv_station_name
        val image: ImageView = itemView.iv_station_logo

        fun bind(data: Data) {
            name.text = data.title
            image.setImageResource(data.image)
        }
    }
}