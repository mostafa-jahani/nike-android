package com.example.nikeapp.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.nikeapp.databinding.ItemSliderBinding
import com.example.nikeapp.network.models.home.Slider
import okio.blackholeSink
import javax.inject.Inject


class SliderAdapter @Inject constructor() : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    private lateinit var binding: ItemSliderBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: Slider) {
            binding.sliderPoster.load(item.image) {
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(25f))
            }
        }

    }


    //Diff Utils
    private val differCallback = object : DiffUtil.ItemCallback<Slider>() {
        override fun areItemsTheSame(oldItem: Slider, newItem: Slider): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Slider, newItem: Slider): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}