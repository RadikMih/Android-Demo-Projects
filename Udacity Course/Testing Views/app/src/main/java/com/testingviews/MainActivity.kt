package com.testingviews

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.testingviews.R.drawable.*
import com.testingviews.databinding.ActivityMainBinding
import com.testingviews.home.HomeFragment
import com.testingviews.player.AudioService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_now_playing.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var isPlaying = false

    var audioService: AudioService? = null
    var isBound = false
    //  private val uri: Uri = Uri.parse("https://fm4shoutcast.sf.apa.at/;")
    private val bottomNavigationItemListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    item.isChecked = true
                }
                R.id.nav_favorites -> {

                }
                R.id.nav_search -> {
                    replaceFragment(SearchFragment())
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

        bottom_navigation.setOnNavigationItemSelectedListener(bottomNavigationItemListener)
        replaceFragment(HomeFragment())

        isPlaying = false
        var isLiked = false

        // consider data binding
        val likeDislikeButton: Button = like_dislike_button
        val playPauseButton: Button = play_pause_button

        val intent = Intent(this, AudioService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)

        if (savedInstanceState != null) {
            val playing = savedInstanceState.get("state") as Boolean
            changePlayPause(playing, playPauseButton)
        }

        playPauseButton.setOnClickListener {
            isPlaying = !isPlaying
            changePlayPause(isPlaying, it)
        }

        likeDislikeButton.setOnClickListener {
            isLiked = !isLiked
            changeLikeDislike(isLiked, it)
        }
    }

    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as AudioService.LocalBinder
            audioService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    private fun changePlayPause(isPlaying: Boolean, it: View) {
        val resId: Int
        if (isPlaying) {
            resId = ic_pause_circle_outline
            audioService?.play()
        } else {
            resId = ic_play_circle_outline
            audioService?.stop()
        }
        it.background = ContextCompat.getDrawable(this, resId)
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
      //unbindService(myConnection)
    }
}
