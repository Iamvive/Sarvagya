package com.sarvagya.android.ui.festival

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarvagya.android.databinding.ItemFestiveBinding

class FestiveAdapter() : RecyclerView.Adapter<FestiveAdapter.FestiveVM>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestiveVM {
        val viewBinding = ItemFestiveBinding.inflate(LayoutInflater.from(parent.context))
        return FestiveVM(viewBinding)
    }

    override fun onBindViewHolder(holder: FestiveVM, position: Int) {
//        val item = albums[position]
//        holder.bindData(item)
    }

    override fun getItemCount(): Int {
        return 7
    }

    inner class FestiveVM(private val viewBinding: ItemFestiveBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
//        fun bindData(item: MusicPlaylist) {
//            viewBinding.apply {
//                albumIV.loadImage(item.playlistImage)
//                albumTV.text = item.playlistName
//            }
//        }
    }
}