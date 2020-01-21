package com.testingviews

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.testingviews.R.drawable.*
import com.testingviews.discover.DiscoverFragment
import com.testingviews.home.Data
import com.testingviews.home.HomeFragment
import com.testingviews.home.MainViewModel
import com.testingviews.player.AudioService
import com.testingviews.player.SingleItemFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_now_playing.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var selectedStream: String
    var isPlaying = false
    private lateinit var sendDataIntent: Intent
    private lateinit var mainActivityData: Data
    var audioService: AudioService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.i("onCreate")

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        sendDataIntent = Intent(this, AudioService::class.java)
        bottom_navigation.setOnNavigationItemSelectedListener(bottomNavigationItemListener)
        replaceFragment(HomeFragment())

        isPlaying = false
        var isLiked = false

        val uriObserver = Observer<Data> { data ->
            selectedStream = data.stream
            now_playing_title.text = data.title
            if (now_playing_include.visibility == View.GONE) {
                now_playing_include.visibility = View.VISIBLE
            }
            sendDataIntent.action = audioService?.ACTION_PLAY
            mainActivityData = data
            play(mainActivityData)
        }
        viewModel.selected.observe(this, uriObserver)

        LocalBroadcastManager.getInstance(this).registerReceiver(
            mMessageReceiver, IntentFilter("PLAY")
        )

        play_pause_button.setOnClickListener {
            isPlaying = !isPlaying
            changePlayPause(mainActivityData)
        }

        like_dislike_button.setOnClickListener {
            isLiked = !isLiked
            changeLikeDislike(isLiked, it)
        }

        now_playing_title.setOnClickListener {
            replaceFragment(SingleItemFragment())
        }

        startService(sendDataIntent)
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart")
        val intent = Intent(this, AudioService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume ")
        audioService?.getStatus()?.let { changePlayPauseButtons(it) }
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy")
        unbindService(myConnection)
    }

    private fun play(data: Data) {
        if (!isPlaying) {
            isPlaying = true
        }
        changePlayPause(data)
    }

    private fun changePlayPause(data: Data) {
        if (isPlaying) {
            audioService?.play(data)
        } else {
            audioService?.pause()
        }
        changePlayPauseButtons(isPlaying)
    }

    private fun changePlayPauseButtons(state: Boolean) {
        val resId: Int = if (state) ic_pause_circle_outline else ic_play_circle_outline
        Timber.i("%s", resId)
        play_pause_button.background = ContextCompat.getDrawable(this, resId)
    }

    private fun changeLikeDislike(isLiked: Boolean, it: View) {
        val resId = if (isLiked) ic_favorite else ic_favorite_border
        it.background = ContextCompat.getDrawable(this, resId)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val state = intent.getBooleanExtra("STATE", isPlaying)
            changePlayPauseButtons(state)
        }
    }

    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as AudioService.LocalBinder
            audioService = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            audioService = null
        }
    }

    private val bottomNavigationItemListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    item.isChecked = true
                }
                R.id.nav_favorites -> {
                    replaceFragment(SingleItemFragment())
                    item.isChecked = true
                }
                R.id.nav_search -> {
                    replaceFragment(DiscoverFragment())
                    item.isChecked = true
                }
            }
            false
        }
}
