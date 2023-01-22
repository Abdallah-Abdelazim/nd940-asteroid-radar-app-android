package com.abdallah_abdelazim.asteroidradar.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdallah_abdelazim.asteroidradar.R
import com.abdallah_abdelazim.asteroidradar.databinding.ItemAsteroidBinding
import com.abdallah_abdelazim.asteroidradar.ui.model.AsteroidUiModel

class AsteroidsAdapter(private val onItemClick: (asteroidUiModel: AsteroidUiModel) -> Unit) :
    RecyclerView.Adapter<AsteroidsAdapter.AsteroidViewHolder>() {

    var asteroidUiModels: List<AsteroidUiModel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)
        val binding: ItemAsteroidBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_asteroid, parent, false)
        return AsteroidViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.asteroidUiModel = asteroidUiModels!![position]
    }

    override fun getItemCount(): Int = asteroidUiModels?.size ?: 0

    class AsteroidViewHolder(
        private val binding: ItemAsteroidBinding,
        private val onItemClick: (asteroidUiModel: AsteroidUiModel) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        var asteroidUiModel: AsteroidUiModel? = null
            set(value) {
                field = value
                binding.asteroid = value
                binding.root.setOnClickListener {
                    asteroidUiModel?.let(onItemClick)
                }
            }

    }

}

