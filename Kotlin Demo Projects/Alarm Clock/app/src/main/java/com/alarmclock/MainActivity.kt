package com.alarmclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendData = SendData(applicationContext)
        showTimeTextView.text = "${sendData.getHour()}:${sendData.getMinute()}"
    }

    fun setTime(view: View) {
        val time = TimeSelector()
        val fragmentManager = supportFragmentManager
        time.show(fragmentManager, "Select time")
    }

    fun showTime(hour: Int, minute: Int) {
        showTimeTextView.text = "$hour:$minute"

        val sendData = SendData(applicationContext)
        sendData.saveData(hour, minute)
        sendData.setAlarm()
    }


}
