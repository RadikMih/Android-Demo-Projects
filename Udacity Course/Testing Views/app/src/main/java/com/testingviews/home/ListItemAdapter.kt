package com.testingviews.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.testingviews.R
import kotlinx.android.synthetic.main.layout_content_list_item.view.*


class ListItemAdapter(private var items: List<Data>, private var clickListener: ClickListener) :
    RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ListItemViewHolder(inflater, parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val data: Data = items[position]
        holder.bind(data, clickListener)
    }

    fun submitList(list: List<Data>) {
        items = list as ArrayList<Data>
    }

    class ListItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(
            inflater.inflate(
                R.layout.layout_content_list_item,
                parent,
                false
            )
        ) {


        val layout = itemView.list_item_parent_layout
        val name: TextView = itemView.tv_station_name
        val image: ImageView = itemView.iv_station_logo
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

interface ClickListener {
    fun onClick(data: Data)
}