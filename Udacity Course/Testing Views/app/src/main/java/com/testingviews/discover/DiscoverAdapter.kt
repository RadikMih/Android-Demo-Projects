package com.testingviews.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.testingviews.R
import com.testingviews.home.ClickListener
import com.testingviews.home.Data
import kotlinx.android.synthetic.main.layout_discover_single_item.view.*

class DiscoverAdapter(private var items: List<Data>, private var clickListener: ClickListener) :
    RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DiscoverViewHolder(inflater, parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        val data: Data = items[position]
        holder.bind(data, clickListener)
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

        val layout: ConstraintLayout = itemView.discover_item_parent_layout
        val name: TextView = itemView.tv_discover_station_name
        val image: ImageView = itemView.iv_discovery_station_logo
        var link: String = ""

        fun bind(data: Data, listener: ClickListener) {
            name.text = data.title
            image.setImageResource(data.image)
            link = data.stream

            layout.setOnClickListener {
                listener.onClick(data)
            }
        }
    }
}