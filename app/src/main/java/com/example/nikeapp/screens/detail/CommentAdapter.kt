package com.example.nikeapp.screens.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeapp.databinding.ItemCommentBinding
import com.example.nikeapp.network.models.Comment
import com.example.nikeapp.network.models.home.Product
import javax.inject.Inject


class CommentAdapter @Inject constructor() : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private lateinit var binding: ItemCommentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

//    override fun getItemCount(): Int = differ.currentList.size
    override fun getItemCount(): Int = if (differ.currentList.size > 3) 3 else differ.currentList.size



    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: Comment) {
            binding.apply {
                commentTitleTv.text = item.title
                commentDateTv.text = item.date
                commentAuthor.text = item.author.email
                commentContentTv.text = item.content
            }
        }

    }










    //Diff Utils
    private val differCallback = object : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}