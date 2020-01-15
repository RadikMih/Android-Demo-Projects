package com.testingviews

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.testingviews.R.drawable.*
import com.testingviews.databinding.ActivityMainBinding
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
    private lateinit var binding: ActivityMainBinding
    var isPlaying = false

    // consider data binding
    private lateinit var likeDislikeButton: Button
    lateinit var playPauseButton: Button
    lateinit var nowPlayingTitle: TextView


    var audioService: AudioService? = null
    var isBound = false

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.i("onCreate")
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Get the ViewModel.
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        bottom_navigation.setOnNavigationItemSelectedListener(bottomNavigationItemListener)
        replaceFragment(HomeFragment())
        playPauseButton = play_pause_button
        likeDislikeButton = like_dislike_button
        nowPlayingTitle = now_playing_title
        val nowPlayingInclude = now_playing_include

        isPlaying = false
        var isLiked = false

        val uriObserver = Observer<Data> { data ->
            selectedStream = data.stream
            nowPlayingTitle.text = data.title
            if (nowPlayingInclude.visibility == View.GONE) {
                nowPlayingInclude.visibility = View.VISIBLE
            }
            play(selectedStream)
        }
        viewModel.selected.observe(this, uriObserver)

        if (savedInstanceState != null) {
            isPlaying = savedInstanceState.get("state") as Boolean
        }


        playPauseButton.setOnClickListener {
            isPlaying = !isPlaying
            changePlayPause(selectedStream)
        }

        likeDislikeButton.setOnClickListener {
            isLiked = !isLiked
            changeLikeDislike(isLiked, it)
        }

        val intent = Intent(this, AudioService::class.java)
        startService(intent)

    }

    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as AudioService.LocalBinder
            audioService = binder.getService()
            isBound = true
            //   changePlayPause(isPlaying, playPauseButton) // class
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    fun play(stream: String) {
        if (!isPlaying) {
            isPlaying = true
        }
        changePlayPause(stream)
    }

    private fun changePlayPause(newStream: String) {
        val resId: Int
        if (isPlaying) {
            resId = ic_pause_circle_outline
            audioService?.play(newStream)
        } else {
            resId = ic_play_circle_outline
            audioService?.stop()

        }
        playPauseButton.background = ContextCompat.getDrawable(this, resId)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("SAVED")
        outState.putBoolean("state", isPlaying)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // called after onStart
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart")
        val intent = Intent(this, AudioService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume")
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
}
