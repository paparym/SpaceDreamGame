package com.space.spacedreamspace

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.space.spacedreamspace.databinding.FragmentGameBinding
import com.space.spacedreamspace.db.GameDatabase
import com.space.spacedreamspace.db.LevelDao
import com.space.spacedreamspace.di.DaggerFragmentComponent
import com.space.spacedreamspace.di.FragmentComponent
import com.space.spacedreamspace.items.Hero
import com.space.spacedreamspace.items.Enemy
import com.space.spacedreamspace.items.Level
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GameFragment : Fragment(R.layout.fragment_game) {

    val args: GameFragmentArgs by navArgs()


    lateinit var binding: FragmentGameBinding
    lateinit var uiScope: CoroutineScope
    lateinit var cdt: CountDownTimer

    lateinit var levelDao: LevelDao

    @Inject
    lateinit var enemy1: Enemy

    @Inject
    lateinit var enemy2: Enemy

    @Inject
    lateinit var enemy3: Enemy

    @Inject
    lateinit var enemy4: Enemy




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)
        levelDao = GameDatabase.getDatabase(requireContext()).levelDao

        var time = args.level.time
        var timeInMillis: Long = TimeUnit.SECONDS.toMillis(time.toLong()) + 1000

        repeatBackgroundTop(binding.bg1, binding.bg12)


        binding.restart.setOnClickListener {
            findNavController().navigate(
                R.id.gameFragment,
                arguments,
                NavOptions.Builder().setPopUpTo(R.id.gameFragment, true)
                    .setEnterAnim(android.R.anim.fade_in)
                    .setExitAnim(android.R.anim.fade_out)
                    .build()
            )
        }

        binding.goodjob.setOnClickListener {
            findNavController().popBackStack()
        }

        cdt = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timerTextView.text = "$time"
                time--
            }

            override fun onFinish() {
                uiScope.cancel()
                endGameYouWin()
            }

        }.start()

        uiScope = CoroutineScope(Dispatchers.Main)
        val currentJob = uiScope.launch {

            delay(100)

            val enemyTemplate = Enemy(binding.frame, args.level.speed)
            val heroTemplate = Hero(binding.heroMain)

            val fragmentComponent: FragmentComponent = DaggerFragmentComponent.builder()
                .container(enemyTemplate.container)
                .speed(enemyTemplate.speed)
                .heroIv(heroTemplate.iv)
                .build()

            fragmentComponent.inject(this@GameFragment)

            enemy1.start(binding.enemy, heroTemplate, this)
            delay(1000)
            enemy2.start(binding.enemy2, heroTemplate, this)
            delay(1000)
            enemy3.start(binding.enemy3, heroTemplate, this)
            delay(1000)
            enemy4.start(binding.enemy4, heroTemplate, this)


        }

        currentJob.invokeOnCompletion {
            endGameYouLost()
            cdt.cancel()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        cdt.cancel()
        uiScope.cancel()
    }

    fun endGameYouLost() {
        fadeIn(binding.restart, 1200)
        fadeAll(
            1000,
            binding.enemy,
            binding.enemy2,
            binding.enemy3,
            binding.enemy4,
            binding.heroMain,
            binding.timerTextView
        )
    }

    fun endGameYouWin() {
        binding.restart.visibility = View.GONE
        scaler(binding.goodjob, 1.1f, 300)
        fadeIn(binding.goodjob, 500)
        levelDao.insert(Level(args.level.levelID+1, args.level.speed+2, args.level.time+4, true))
    }


}
