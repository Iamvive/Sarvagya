package com.sarvagya.android.ui.music.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarvagya.android.databinding.ItemAlbumBinding
import com.sarvagya.android.extension.loadImage
import com.sarvagya.android.ui.music.data.playlist.Playlist
import com.sarvagya.android.ui.music.view.AlbumAdapter.*

class AlbumAdapter() : RecyclerView.Adapter<AlbumVH>() {

    private val albums = mutableListOf<Playlist>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumVH {
        val viewBinding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context))
        return AlbumVH(viewBinding)
    }

    override fun onBindViewHolder(holder: AlbumVH, position: Int) {
        val item = albums[position]
        holder.bindData(item)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    fun setData(playlist: List<Playlist>) {
        albums.addAll(playlist)
        notifyDataSetChanged()
    }

    inner class AlbumVH(private val viewBinding: ItemAlbumBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(item: Playlist) {
            viewBinding.apply {
                albumIV.loadImage(item.image)
                albumTV.text = item.name
            }
            viewBinding.cardView
        }
    }

}
