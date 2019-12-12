package com.testingviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val items: ArrayList<String> = ArrayList()
        for (i in 1..6) {
            items.add("Item $i")
        }


        recently_played_list.layoutManager = LinearLayoutManager(activity)
        recently_played_list.adapter = ListItemAdapter(items)

        return inflater.inflate(R.layout.fragment_home, OrientationHelper.HORIZONTAL, false)
    }
}