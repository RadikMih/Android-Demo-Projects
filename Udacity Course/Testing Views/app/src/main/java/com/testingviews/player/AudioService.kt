package com.testingviews.player

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.net.wifi.WifiManager.WifiLock
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util


private const val ACTION_PLAY = "ACTION_PLAY"
private const val ACTION_PAUSE = "ACTION_PAUSE"
private const val ACTION_STOP = "ACTION_STOP"



class AudioService : Service() {

    private val iBinder: IBinder = LocalBinder()
    private var exoPlayer: SimpleExoPlayer? = null
    private var notificationManager: PlayerNotificationManager? = null
    private var audioManager: AudioManager? = null
    private val streamUri: String? = null
    private var wifiLock: WifiLock? = null
    private var mediaSession: MediaSessionCompat? = null
    private val mediaUri: Uri = Uri.parse("https://fm4shoutcast.sf.apa.at/;")

    override fun onCreate() {
        super.onCreate()

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val trackSelector = DefaultTrackSelector()

        mediaSession = MediaSessionCompat(this, javaClass.simpleName)
        mediaSession?.isActive = true
        mediaSession?.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)

        exoPlayer = ExoPlayerFactory.newSimpleInstance(applicationContext, trackSelector)

        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            applicationContext,
            Util.getUserAgent(applicationContext, "yourApplicationName")
        )


        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaUri)
        exoPlayer?.prepare(mediaSource)



    }

    fun releasePlayer() {
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

    class LocalBinder : Binder() {
        fun getService(): AudioService {
            return AudioService()
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        stopSelf()
        return super.onUnbind(intent)
    }

    /// https://github.com/yusufcakmak/ExoPlayerSample/blob/master/app/src/main/java/com/yusufcakmak/exoplayersample/RadioPlayerActivity.kt
}