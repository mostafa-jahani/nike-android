package com.example.nikeapp.screens.home

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.nikeapp.databinding.ItemProductBinding
import com.example.nikeapp.network.models.home.Product
import com.example.nikeapp.utils.formatPrice
import com.example.nikeapp.utils.implementSpringAnimationTrait
import javax.inject.Inject

class PopularProductsAdapter @Inject constructor() : RecyclerView.Adapter<PopularProductsAdapter.ViewHolder>() {

    private lateinit var binding: ItemProductBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: Product) {
            binding.apply {

                productImg.load(item.image) {
                    crossfade(true)
                    crossfade(1000)
                    transformations(RoundedCornersTransformation(40f))
                }

                productTitleTv.text = item.title
                currentPriceTv.text = formatPrice(item.price)
                previousPriceTv.text = formatPrice(item.previous_price)
                previousPriceTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            binding.root.apply {
                implementSpringAnimationTrait()
                setOnClickListener {  }
            }
        }

    }


    //Diff Utils
    private val differCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}