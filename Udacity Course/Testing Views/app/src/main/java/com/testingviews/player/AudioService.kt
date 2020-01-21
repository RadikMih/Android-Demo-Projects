package com.testingviews.player

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.text.TextUtils
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.testingviews.R
import com.testingviews.home.Data
import timber.log.Timber


class AudioService : Service() {
    val ACTION_PLAY = "ACTION_PLAY"
    val ACTION_PAUSE = "ACTION_PAUSE"
    val ACTION_STOP = "ACTION_STOP"

    private val iBinder: IBinder = LocalBinder()
    private var exoPlayer: SimpleExoPlayer? = null
    private lateinit var mediaSource: MediaSource
    private var dataSourceFactory: DefaultDataSourceFactory? = null
    private var mediaSession: MediaSessionCompat? = null
    private var notificationManager: MediaNotificationManager? = null
    private lateinit var status: String
    private lateinit var audioServiceData: Data
    private var streamUri: String = "https://fm4shoutcast.sf.apa.at/;"

    override fun onCreate() {
        super.onCreate()

        val trackSelectionFactory = AdaptiveTrackSelection.Factory()
        val trackSelector = DefaultTrackSelector(trackSelectionFactory)

        mediaSession = MediaSessionCompat(this, javaClass.simpleName)
        mediaSession?.isActive = true
        notificationManager = MediaNotificationManager(this)
        exoPlayer = ExoPlayerFactory.newSimpleInstance(applicationContext, trackSelector)
        audioServiceData = Data("Title", R.drawable.ic_discover_genre, streamUri)
        prepareMediaSource()
        status = PlaybackStatus.IDLE
        Timber.i("onCreate")
        // Monitor ExoPlayer events.
        exoPlayer?.addListener(PlayerEventListener())
    }

    private fun sendMessageToActivity(state: Boolean) {
        val intent = Intent("PLAY")
        intent.putExtra("STATE", state)
        LocalBroadcastManager.getInstance(application).sendBroadcast(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.i("onStartCommand")
        val action = intent?.action
        Timber.i("action = $action")
        if (TextUtils.isEmpty(action)) {
            return START_NOT_STICKY
        }

        when {
            action.equals(ACTION_PLAY, ignoreCase = true) -> {
                play(audioServiceData)
            }
            action.equals(ACTION_PAUSE, ignoreCase = true) -> {
                pause()
            }
            action.equals(ACTION_STOP, ignoreCase = true) -> {
                stop()
            }
        }
        return START_NOT_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        stopSelf()
        super.onTaskRemoved(rootIntent)
    }

    private fun prepareMediaSource() {
        val audioAttributes: AudioAttributes = AudioAttributes.Builder()
            .setUsage(C.USAGE_MEDIA)
            .setContentType(C.CONTENT_TYPE_MUSIC)
            .build()

        dataSourceFactory = DefaultDataSourceFactory(
            applicationContext,
            Util.getUserAgent(applicationContext, "Radio Project")
        )

        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(setMediaUrl(streamUri))

        exoPlayer?.apply {
            setAudioAttributes(audioAttributes, true)
            prepare(mediaSource)
        }
    }

    fun play(data: Data) {
        audioServiceData = data
        streamUri = audioServiceData.stream
        setMediaUrl(streamUri)
        prepareMediaSource()
        exoPlayer?.playWhenReady = true
        status = PlaybackStatus.PLAYING
        notificationManager?.startNotify(status, audioServiceData)
    }

    fun pause() {
        exoPlayer?.playWhenReady = false
        status = PlaybackStatus.PAUSED
        notificationManager?.startNotify(status, audioServiceData)
    }

    fun stop() {
        exoPlayer?.playWhenReady = false
        notificationManager?.cancelNotify()
    }

    private fun releasePlayer() {
        exoPlayer?.stop()
        exoPlayer?.release()
        notificationManager?.cancelNotify()
        exoPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
        Timber.i("onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Timber.i("onBind")
        return iBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        // stopSelf()
        Timber.i("onUnbind")
        return super.onUnbind(intent)
    }

    inner class LocalBinder : Binder() {
        fun getService(): AudioService {
            return this@AudioService
        }
    }

    fun getMediaSession(): MediaSessionCompat? {
        return mediaSession
    }

    fun getStatus(): Boolean? {
        return exoPlayer?.isPlaying
    }

    private fun setMediaUrl(link: String): Uri {
        return Uri.parse(link)
    }

    private inner class PlayerEventListener : Player.EventListener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            status = when (playbackState) {
                Player.STATE_BUFFERING -> PlaybackStatus.LOADING
                Player.STATE_ENDED -> PlaybackStatus.STOPPED
                Player.STATE_IDLE -> PlaybackStatus.IDLE
                Player.STATE_READY -> if (playWhenReady) PlaybackStatus.PLAYING else PlaybackStatus.PAUSED
                else -> PlaybackStatus.IDLE
            }

            sendMessageToActivity(getStatus()!!)
            if (status != PlaybackStatus.IDLE) {
                notificationManager?.startNotify(status, audioServiceData)
            }
        }
    }
}

