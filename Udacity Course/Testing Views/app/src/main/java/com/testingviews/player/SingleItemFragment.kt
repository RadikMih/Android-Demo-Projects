package com.testingviews.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.testingviews.R
import com.testingviews.home.Data
import com.testingviews.home.MainViewModel
import kotlinx.android.synthetic.main.fragment_single_item.*


class SingleItemFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance(): SingleItemFragment = SingleItemFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uriObserver = Observer<Data> { data ->
            station_name_text.text = data.title
            station_logo_image_view.setImageResource(data.image)
        }
        viewModel.selected.observe(this, uriObserver)
    }
}