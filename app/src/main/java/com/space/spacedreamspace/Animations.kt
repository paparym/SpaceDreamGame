package com.space.spacedreamspace

import android.animation.*
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.core.animation.addListener
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import java.util.*
import kotlin.random.Random.Default.nextInt
import android.animation.ValueAnimator
import android.animation.ValueAnimator.*
import android.os.Handler
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


fun scaler(view: View, value: Float, durationInside: Long) {
    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, value)
    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, value)
    ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY)
        .apply {
            interpolator = LinearInterpolator()
            duration = durationInside
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }
        .start()
}

fun fadeAll(duration: Long, vararg views: View) {
    for (view in views) {
        val animator = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
        animator.duration = duration
        animator.disableViewDuringAnimation(view)
        animator.disableViewVisibility(view)
        animator.start()
    }

}

fun fadeIn(view: View, duration: Long) {
    val animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f)
    view.visibility = View.VISIBLE
    animator.duration = duration
    animator.disableViewDuringAnimation(view)
    animator.start()
}


fun ObjectAnimator.disableViewDuringAnimation(view: View) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            view.isEnabled = false
        }

        override fun onAnimationEnd(animation: Animator?) {
            view.isEnabled = true
        }
    })
}

fun ObjectAnimator.disableViewVisibility(view: View) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            view.visibility = View.VISIBLE
            view.isEnabled = false
        }

        override fun onAnimationEnd(animation: Animator?) {
            view.visibility = View.GONE
        }
    })
}


fun isCollisionDetected(v1: View, v2: View): Boolean {
    val R1 = Rect()
    v1.getHitRect(R1)
    val R2 = Rect()
    v2.getHitRect(R2)
    return Rect.intersects(R1, R2)
}

fun repeatBackgroundTop(bgOne: View, bgTwo: View) {
    ofFloat(0.0f, 1.0f)
        .apply {
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            duration = 10000L
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                val height: Float = bgOne.height.toFloat()
                val translationY = height * progress
                bgOne.translationY = translationY
                bgTwo.translationY = translationY - height
            }
        }.start()
}