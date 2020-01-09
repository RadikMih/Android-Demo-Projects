package com.testingviews.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testingviews.R
import com.testingviews.databinding.FragmentHomeBinding
import java.lang.Exception


class HomeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel


    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.selected.observe(this, Observer<Data> { item ->
            item.title //example
        })

        retainInstance = true
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


//    companion object {
//        fun newInstance(): HomeFragment = HomeFragment()
//    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)


//        binding.apply {
//            // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
//            lifecycleOwner = this@HomeFragment
//
//            // Giving the binding access to the HomeViewModel
//            homeViewModel = viewModel
//        }

        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    // ================ DATA =================================

    val items = listOf(
        Data(
            "BBC - Radio 1",
            R.drawable.ic_launcher_background,
            "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_p"
        ),
        Data(
            "BBC - Radio 2",
            R.drawable.default_no_station_logo,
            "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio2_mf_p"
        ),
        Data(
            "BBC - Radio 3",
            R.drawable.ic_launcher_background,
            "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio3_mf_p"
        ),
        Data(
            "BBC - Radio 4",
            R.drawable.ic_discover_country,
            "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio4fm_mf_p"
        ),
        Data(
            "BBC - Radio 5 live",
            R.drawable.ic_discover_moods,
            "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio5live_mf_p"
        ),
        Data(
            "BBC - Radio 6",
            R.drawable.ic_discover_search,
            "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_6music_mf_p"
        ),
        Data(
            "BBC Radio Asian Network",
            R.drawable.ic_launcher_background,
            "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_asianet_mf_p"
        ),
        Data(
            "BBC World Service",
            R.drawable.ic_launcher_background,
            "http://bbcwssc.ic.llnwd.net/stream/bbcwssc_mp1_ws-eieuk"
        ),
        Data("FM 4", R.drawable.ic_launcher_background, "https://fm4shoutcast.sf.apa.at/;")
    )

    val categories = arrayListOf(
        ParentData("Recently played", items),
        ParentData("Discover", items),
        ParentData("Favorites", items)
    )


}