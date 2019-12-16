package com.testingviews.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.testingviews.R
import kotlinx.android.synthetic.main.layout_row_list.*


class HomeFragment : Fragment() {

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

        child_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = ListItemAdapter(items)
        }
    }

    // ================ DATA =================================


    private val items = listOf(
        Data("Test 1", R.drawable.ic_launcher_background),
        Data("Test 2", R.drawable.default_no_station_logo),
        Data("Test 3", R.drawable.ic_discover_country),
        Data("Test 4", R.drawable.ic_discover_genre),
        Data("Test 5", R.drawable.ic_discover_moods),
        Data("Test 6", R.drawable.ic_discover_search),
        Data("Test 7", R.drawable.ic_launcher_background)
    )

    private val categories = arrayListOf(
        ParentData("Recently played", items),
        ParentData("Discover", items),
        ParentData("Favorites", items)
    )


}