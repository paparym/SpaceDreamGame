package com.space.spacedreamspace

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.space.spacedreamspace.databinding.FragmentLevelChooseBinding
import com.space.spacedreamspace.db.GameDatabase
import com.space.spacedreamspace.db.LevelDao
import com.space.spacedreamspace.items.Level
import com.space.spacedreamspace.items.LevelAdapter

class LevelChooseFragment: Fragment(R.layout.fragment_level_choose) {

    lateinit var binding: FragmentLevelChooseBinding
    lateinit var levelDao: LevelDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLevelChooseBinding.bind(view)
        levelDao = GameDatabase.getDatabase(requireContext()).levelDao

        repeatBackgroundTop(binding.bg1, binding.bg12)

        levelDao.insert(
            Level(1, 8, 4)
        )

        val levelAdapter = LevelAdapter(levelDao.getAll())
        binding.recyclerView.adapter = levelAdapter

    }
}