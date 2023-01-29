package com.sarvagya.android.ui.music.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarvagya.android.databinding.ItemSongsBinding
import com.sarvagya.android.extension.loadCircleCropImage
import com.sarvagya.android.ui.music.data.staticmodel.MusicPlaylist
import com.sarvagya.android.ui.music.view.SongsAdapter.SongsVH

class SongsAdapter(
    private val songs: List<MusicPlaylist> = emptyList()
) : RecyclerView.Adapter<SongsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsVH {
        val viewBinding = ItemSongsBinding.inflate(LayoutInflater.from(parent.context))
        return SongsVH(viewBinding)
    }

    override fun onBindViewHolder(holder: SongsVH, position: Int) {
        val item = songs[position]
        holder.bindData(item)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    inner class SongsVH(private val view: ItemSongsBinding) : RecyclerView.ViewHolder(view.root) {
        fun bindData(item: MusicPlaylist) {
            view.apply {
                songIV.loadCircleCropImage(item.playlistImage)
                songName.text = item.musicName
                singerName.text = item.artistName
            }
        }
    }

}
