package com.space.spacedreamspace.di

import android.view.ViewGroup
import android.widget.ImageView
import com.space.spacedreamspace.items.Enemy
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class EnemyModule {

    @Provides
    fun provideEnemy(container: ViewGroup, speed: Int): Enemy {
        return Enemy(container, speed)
    }

}