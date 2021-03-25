package com.space.spacedreamspace.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.space.spacedreamspace.LevelChooseFragment
import com.space.spacedreamspace.LevelChooseFragmentDirections
import com.space.spacedreamspace.databinding.LevelItemBinding

class LevelAdapter(private val data: List<Level>) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        return LevelViewHolder(LevelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class LevelViewHolder(val binding: LevelItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(level: Level) {
            binding.levelDescription.text = "Level ${level.levelID}"
            itemView.setOnClickListener {
                it.findNavController()
                    .navigate(LevelChooseFragmentDirections.actionLevelChooseFragmentToGameFragment(level))
            }
        }
    }
}