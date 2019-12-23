package com.testingviews.player

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

//private const val ACTION_PLAY = "ACTION_PLAY"
//private const val ACTION_PAUSE = "ACTION_PAUSE"
//private const val ACTION_STOP = "ACTION_STOP"
//private const val RADIO_URL = "https://fm4shoutcast.sf.apa.at/;"

class AudioService : Service() {

    private val iBinder: IBinder = LocalBinder()
    private var exoPlayer: SimpleExoPlayer? = null
    private var mediaSource: MediaSource? = null
    private var dataSourceFactory: DefaultDataSourceFactory? = null

    //    private var notificationManager: PlayerNotificationManager? = null
    //    private var audioManager: AudioManager? = null
    //    private val streamUri: String? = null
    //    private var wifiLock: WifiLock? = null
    //    private var mediaSession: MediaSessionCompat? = null
    private val mediaUri: Uri = Uri.parse("https://fm4shoutcast.sf.apa.at/;")

    override fun onCreate() {
        super.onCreate()

//        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
//        val trackSelector = DefaultTrackSelector()
//        mediaSession = MediaSessionCompat(this, javaClass.simpleName)
//        mediaSession?.isActive = true
//        mediaSession?.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)

//        val bandwidthMeter = DefaultBandwidthMeter()
//        val trackSelectionFactory =
//            AdaptiveTrackSelection.Factory(bandwidthMeter)
//        val trackSelector = DefaultTrackSelector(trackSelectionFactory)
        //exoPlayer = ExoPlayerFactory.newSimpleInstance(applicationContext, trackSelector)
        exoPlayer = ExoPlayerFactory.newSimpleInstance(applicationContext)
        // exoPlayer?.addListener(this)  // , Player.EventListener

        dataSourceFactory = DefaultDataSourceFactory(
            applicationContext,
            Util.getUserAgent(applicationContext, "Radio Project")
        )

        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaUri)
        exoPlayer?.prepare(mediaSource)
    }

    fun play() {
        exoPlayer?.playWhenReady = true
    }

    fun stop() {
        exoPlayer?.playWhenReady = false
    }

    private fun releasePlayer() {
        exoPlayer?.stop()
        exoPlayer?.release()
        exoPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return iBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        stopSelf()
        return super.onUnbind(intent)
    }

    inner class LocalBinder : Binder() {
        fun getService(): AudioService {
            return this@AudioService
        }
    }
}