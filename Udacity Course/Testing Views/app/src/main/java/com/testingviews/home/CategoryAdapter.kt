package com.testingviews.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testingviews.R
import kotlinx.android.synthetic.main.layout_row_list.view.*

class CategoryAdapter(var categories: List<ParentData>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    val viewPool = RecyclerView.RecycledViewPool()
    private lateinit var category: ParentData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_row_list, parent, false)

        return CategoryViewHolder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        category = categories[position]

        holder.bind(category)
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.category_name_text_view)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.child_recycler_view)
        private val childLayoutManager = LinearLayoutManager(
            itemView.child_recycler_view.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        fun bind(data: ParentData) {
            childLayoutManager.initialPrefetchItemCount = 4
            title.text = data.title
            recyclerView.apply {
                layoutManager = childLayoutManager
                overScrollMode = View.OVER_SCROLL_NEVER
                adapter = ListItemAdapter(category.children)
                setRecycledViewPool(viewPool)
            }
        }
    }
}
