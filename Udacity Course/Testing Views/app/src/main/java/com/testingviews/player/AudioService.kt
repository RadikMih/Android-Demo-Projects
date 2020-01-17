package com.testingviews.player

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
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
    private var status: String? = null

    private var audioManager: AudioManager? = null
    private var streamUri: String = "https://fm4shoutcast.sf.apa.at/;"
    private var mediaUri: Uri = Uri.parse(streamUri)

    lateinit var focusRequest: AudioFocusRequest
    //    private var wifiLock: WifiLock? = null
//    var result: Int? = audioManager?.requestAudioFocus(
//        this, AudioManager.STREAM_MUSIC,
//        AudioManager.AUDIOFOCUS_GAIN
//    )

    override fun onCreate() {
        super.onCreate()
       // audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

//        mediaSession?.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)

        val trackSelectionFactory = AdaptiveTrackSelection.Factory()
        val trackSelector = DefaultTrackSelector(trackSelectionFactory)


        mediaSession = MediaSessionCompat(this, javaClass.simpleName)
        mediaSession?.isActive = true

        notificationManager = MediaNotificationManager(this)
        exoPlayer = ExoPlayerFactory.newSimpleInstance(applicationContext, trackSelector)

        prepareMediaSource()
        status = PlaybackStatus.IDLE
        Timber.i("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.i("onStartCommand")
        return START_STICKY
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

    fun play(url: String) {
        streamUri = url
        setMediaUrl(streamUri)
        prepareMediaSource()
        exoPlayer?.playWhenReady = true
        notificationManager?.startNotify()
    }

    fun pause() {
        exoPlayer?.playWhenReady = false
    }

    fun stop() {
        exoPlayer?.playWhenReady = false
        notificationManager?.cancelNotify()
    }

    private fun releasePlayer() {
        exoPlayer?.stop()
        exoPlayer?.release()
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

//    override fun onAudioFocusChange(focusChange: Int) {
//        when (focusChange) {
//            AudioManager.AUDIOFOCUS_GAIN -> {
//                if (exoPlayer?.volume != 1f) {
//                    exoPlayer?.volume = 1f
//                }
//                play(streamUri)
//            }
//            AudioManager.AUDIOFOCUS_LOSS -> stop()
//            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> pause()
//            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> exoPlayer?.volume = 0.7f
//        }
//    }
}