package com.testingviews.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testingviews.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.parent_recycler_view)
        recyclerView.apply {
            overScrollMode = View.OVER_SCROLL_NEVER
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = CategoryAdapter(categories)
        }

    }

    // ================ DATA =================================

     val items = listOf(
        Data("Test 1", R.drawable.ic_launcher_background),
        Data("Test 2", R.drawable.default_no_station_logo),
        Data("Test 3", R.drawable.ic_launcher_background),
        Data("Test 4", R.drawable.ic_discover_country),
        Data("Test 5", R.drawable.ic_discover_moods),
        Data("Test 6", R.drawable.ic_discover_search),
        Data("Test 7", R.drawable.ic_launcher_background)
    )

     val categories = arrayListOf(
        ParentData("Recently played", items),
        ParentData("Discover", items),
        ParentData("Favorites", items)
    )


}