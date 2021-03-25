package com.space.spacedreamspace

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space.spacedreamspace.databinding.FragmentMenuBinding
import kotlin.system.exitProcess

class MenuFragment: Fragment(R.layout.fragment_menu) {

    lateinit var binding: FragmentMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMenuBinding.bind(view)

        repeatBackgroundTop(binding.bg1, binding.bg12)
        scaler(binding.logo, 1.5f, 5000)
        binding.linePlay.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToLevelChooseFragment())
        }
        binding.lineExit.setOnClickListener {
            exitProcess(0)
        }

    }
}