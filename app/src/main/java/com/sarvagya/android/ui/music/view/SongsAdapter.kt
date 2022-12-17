package com.sarvagya.android.ui.music.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarvagya.android.databinding.ItemSongsBinding
import com.sarvagya.android.ui.music.view.SongsAdapter.SongsVH

class SongsAdapter(
    private val songs: List<String> = emptyList()
) : RecyclerView.Adapter<SongsVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsVH {
        val viewBinding = ItemSongsBinding.inflate(LayoutInflater.from(parent.context))
        return SongsVH(viewBinding.root)
    }

    override fun onBindViewHolder(holder: SongsVH, position: Int) {
        val item = songs[position]
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    inner class SongsVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData() {

        }
    }
}
