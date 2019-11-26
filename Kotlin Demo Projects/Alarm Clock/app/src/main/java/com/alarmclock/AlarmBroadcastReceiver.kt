package com.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.action.equals("com.alarmclock")) {
            val bundle = intent.extras
            val notification = Notifications()
            notification.notify(context!!, bundle!!.getString("message").toString(), 10)
           // Toast.makeText(context, bundle!!.getString("message"), Toast.LENGTH_LONG).show()

        } else if (intent.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val savedData = SendData(context!!)
            savedData.setAlarm()
        }
    }
}