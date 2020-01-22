package com.testingviews.discover


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testingviews.R
import com.testingviews.home.ClickListener
import com.testingviews.home.Data
import com.testingviews.home.HomeFragment
import com.testingviews.home.MainViewModel
import timber.log.Timber

class DiscoverFragment : Fragment(), ClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): DiscoverFragment =
            DiscoverFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
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
            adapter = DiscoverAdapter(HomeFragment().categories[0].children, this@DiscoverFragment)
        }
    }

    override fun onClick(data: Data) {
        viewModel.select(data)
        Timber.e("item clicked - %s", data.stream)
    }
}
