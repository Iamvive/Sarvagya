package com.sarvagya.android.ui.music.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarvagya.android.databinding.ItemAlbumBinding
import com.sarvagya.android.ui.music.view.AlbumAdapter.*

class AlbumAdapter(
    private val albums: List<String> = emptyList()
) : RecyclerView.Adapter<AlbumVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumVH {
        val viewBinding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context))
        return AlbumVH(viewBinding.root)
    }

    override fun onBindViewHolder(holder: AlbumVH, position: Int) {
        val item = albums[position]
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    inner class AlbumVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData() {

        }
    }
}
