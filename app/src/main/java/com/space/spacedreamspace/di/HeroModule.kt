package com.space.spacedreamspace.di

import android.view.ViewGroup
import android.widget.ImageView
import com.space.spacedreamspace.items.Hero
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class HeroModule {

    @Provides
    fun provideHero(iv: ImageView): Hero {
        return Hero(iv)
    }

}