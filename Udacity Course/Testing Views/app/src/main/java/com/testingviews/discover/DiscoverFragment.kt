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
import com.testingviews.home.HomeFragment
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
           layoutManager = GridLayoutManager(activity, 2)
            adapter = DiscoverAdapter(items) // HomeFragment().categories()
        }
    }

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
}
