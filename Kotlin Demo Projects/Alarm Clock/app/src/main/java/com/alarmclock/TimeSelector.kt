package com.alarmclock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment

class TimeSelector : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_time_selector, container, false)
        var doneButton = view.findViewById<Button>(R.id.buDone)
        var timePicker = view.findViewById<TimePicker>(R.id.tp1)

        doneButton.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.showTime(timePicker.hour, timePicker.minute)
            this.dismiss()
        }
        return view
    }


}