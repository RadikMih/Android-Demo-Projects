package com.bestquotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(private val context: Context, private val categories: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(categories[position], context)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val categoryImage = itemView.findViewById<ImageView>(R.id.categoryImageView)
        private val categoryName = itemView.findViewById<TextView>(R.id.categoryTitleTextView)

        fun bindData(category: Category, context: Context) {
            categoryImage.setImageResource(category.resourceId)
            categoryName.text = category.name
        }
    }
}