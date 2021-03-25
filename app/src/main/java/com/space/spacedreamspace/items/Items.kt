package com.space.spacedreamspace.items

import android.view.ViewGroup
import android.widget.ImageView
import com.space.spacedreamspace.R
import com.space.spacedreamspace.isCollisionDetected
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random


class Hero @Inject constructor(val iv: ImageView) {
    init {
        iv.setImageResource(R.drawable.hero_main)
    }
}

class Enemy @Inject constructor(
    val container: ViewGroup,
    val speed: Int,
) {

    fun start(iv: ImageView, afraidOf: Hero, uiScope: CoroutineScope) {
        iv.setImageResource(R.drawable.enemy)
        uiScope.launch {
            while (true) {
                iv.y = -iv.height.toFloat()
                iv.x = Random.nextInt(container.width - iv.width).toFloat()

                while (true) {
                    iv.y += speed
                    delay(20)
                    if (iv.y > container.height) break

                    if (isCollisionDetected(iv, afraidOf.iv)) {
                        uiScope.cancel()
                    }
                }
            }
        }
    }
}

