package com.sarvagya.android.ui.music.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarvagya.android.databinding.ItemAlbumBinding
import com.sarvagya.android.extension.loadImage
import com.sarvagya.android.ui.music.data.staticmodel.MusicPlaylist
import com.sarvagya.android.ui.music.view.AlbumAdapter.*

class AlbumAdapter(
    private val albums: List<MusicPlaylist> = emptyList()
) : RecyclerView.Adapter<AlbumVH>() {

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

    inner class AlbumVH(private val viewBinding: ItemAlbumBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(item: MusicPlaylist) {
            viewBinding.apply {
                albumIV.loadImage(item.playlistImage)
                albumTV.text = item.playlistName
            }
        }
    }

}
