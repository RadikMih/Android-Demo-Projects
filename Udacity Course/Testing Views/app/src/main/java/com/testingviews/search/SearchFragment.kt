package com.testingviews.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.testingviews.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment: Fragment() {

    val list = listOf("Test 1", "Test 2", "Test 3")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner: Spinner = spinner
        spinner.adapter = ArrayAdapter<String>(
            context!!,
            R.layout.layout_spinner_row,
            list
        ).also {
           it.setDropDownViewResource(R.layout.layout_spinner_dropdown)
        }

    }
}