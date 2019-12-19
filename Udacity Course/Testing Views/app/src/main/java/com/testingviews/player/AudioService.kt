package com.testingviews.player

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerNotificationManager


class AudioService : Service() {

    private val iBinder: IBinder = LocalBinder()
    private lateinit var exoPlayer: SimpleExoPlayer
    private var playerNotificationManager: PlayerNotificationManager? = null


    override fun onBind(intent: Intent?): IBinder? {
        return iBinder
    }

    class LocalBinder : Binder() {
        fun getService(): AudioService {
            return AudioService()
        }
    }

    override fun onCreate() {
        super.onCreate()

        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, DefaultTrackSelector())






    }


}