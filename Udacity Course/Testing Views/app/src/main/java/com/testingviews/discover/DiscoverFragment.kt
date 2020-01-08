package com.testingviews.discover


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testingviews.R
import com.testingviews.home.Data
import com.testingviews.home.ListItemAdapter

class DiscoverFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): DiscoverFragment =
            DiscoverFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.discover_recycler_view)
        recyclerView.apply {
            overScrollMode = View.OVER_SCROLL_NEVER
//            layoutManager = GridLayoutManager(activity, 2)
            adapter = ListItemAdapter(items)
        }

    }


    val items = listOf(
        Data("Test 1", R.drawable.ic_launcher_background),
        Data("Test 2", R.drawable.default_no_station_logo),
        Data("Test 3", R.drawable.ic_launcher_background),
        Data("Test 4", R.drawable.ic_discover_country),
        Data("Test 5", R.drawable.ic_discover_moods),
        Data("Test 6", R.drawable.ic_discover_search),
        Data("Test 7", R.drawable.ic_launcher_background)
    )

}
