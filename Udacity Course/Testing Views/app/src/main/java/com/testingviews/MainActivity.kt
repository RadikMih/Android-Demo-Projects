package com.testingviews

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.testingviews.R.drawable.*
import com.testingviews.home.HomeFragment
import com.testingviews.player.AudioService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_now_playing.*

class MainActivity : AppCompatActivity() {

    private val audioService: AudioService = AudioService()
    private val uri: Uri = Uri.parse("https://fm4shoutcast.sf.apa.at/;")
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

        bottom_navigation.setOnNavigationItemSelectedListener(bottomNavigationItemListener)
        replaceFragment(HomeFragment())

        var isPlaying = false
        var isLiked = false

        // TODO - consider data binding
        val likeDislikeButton: Button = like_dislike_button
        val playPauseButton: Button = play_pause_button

        playPauseButton.setOnClickListener {
            isPlaying = !isPlaying
            changePlayPause(isPlaying, it)


        }

        likeDislikeButton.setOnClickListener {
            isLiked = !isLiked
            changeLikeDislike(isLiked, it)
        }


    }

    private fun changePlayPause(isPlaying: Boolean, it: View) {
        val resId = if (isPlaying) ic_pause_circle_outline else ic_play_circle_outline
        it.background = ContextCompat.getDrawable(this, resId)

    }

    private fun changeLikeDislike(isLiked: Boolean, it: View) {
        val resId = if(isLiked) ic_favorite else ic_favorite_border
        it.background = ContextCompat.getDrawable(this, resId)
    }




    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }


}
