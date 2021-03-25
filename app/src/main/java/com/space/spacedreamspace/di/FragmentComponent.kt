package com.space.spacedreamspace.di

import android.view.ViewGroup
import android.widget.ImageView
import com.space.spacedreamspace.GameFragment
import com.space.spacedreamspace.MenuFragment
import com.space.spacedreamspace.items.Enemy
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [EnemyModule::class, HeroModule::class])
interface FragmentComponent {

    fun getEnemy(): Enemy

    fun inject(gameFragment: GameFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun container(vg: ViewGroup): Builder

        @BindsInstance
        fun speed(speed: Int): Builder

        @BindsInstance
        fun heroIv(imageView: ImageView): Builder

        fun build(): FragmentComponent
    }

}