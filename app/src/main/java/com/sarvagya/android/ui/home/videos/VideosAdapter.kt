package com.sarvagya.android.ui.home.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sarvagya.android.databinding.ItemVideoLayoutBinding

class VideosAdapter() : Adapter<VideosAdapter.VideosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val viewBinding = ItemVideoLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return VideosViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 7
    }

    inner class VideosViewHolder(private val binding: ItemVideoLayoutBinding) :
        ViewHolder(binding.root) {
    }

}