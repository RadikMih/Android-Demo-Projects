package com.bestquotes

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation

object AnimationHelper {

    fun animate(holderItemView: View) {
//        val anim = AlphaAnimation(0.0f, 1.0f)
//        anim.duration = 1000
        val translateAnimation = TranslateAnimation(-100f, 0.0f, 0.0f, 0.0f)
        translateAnimation.duration = 1000

        holderItemView.startAnimation(translateAnimation)

    }
}