package com.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.util.*

class SendData {

    var context: Context? = null
    private var sharedRef: SharedPreferences? = null

    constructor(context: Context) {
        this.context = context
        this.sharedRef = context.getSharedPreferences("preference", Context.MODE_PRIVATE)
    }

    fun saveData(hour: Int, minute: Int) {
        val editor = sharedRef!!.edit()
        editor.putInt("hour", hour)
        editor.putInt("minute", minute)
        editor.apply()
    }

    fun getHour(): Int {
        return sharedRef!!.getInt("hour", 0)
    }

    fun getMinute(): Int {
        return sharedRef!!.getInt("minute", 0)
    }

    fun setAlarm() {
        val hour = getHour()
        val minute = getMinute()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(context, AlarmBroadcastReceiver::class.java)
        intent.putExtra("message", "alarm time")
        intent.action = "com.alarmclock"

        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}