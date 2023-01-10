package com.sarvagya.android.ui.videos.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sarvagya.android.databinding.ItemVideoLayoutBinding
import com.sarvagya.android.extension.loadImage
import com.sarvagya.android.ui.videos.VideoAdapterOnClickListener
import com.sarvagya.android.ui.videos.data.models.Video

class VideosAdapter(
    private val listener: VideoAdapterOnClickListener,
    private var list: List<Video> = listOf(),
) : Adapter<VideosAdapter.VideosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val viewBinding = ItemVideoLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return VideosViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bind(list[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(list: List<Video>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class VideosViewHolder(private val binding: ItemVideoLayoutBinding) : ViewHolder(binding.root) {

        fun bind(item: Video) {
            binding.apply {
                videoTitleTV.text = item.title
                durationTV.text = item.duration
                ivVideo.loadImage(item.thumbnail)
            }

            binding.ivVideo.setOnClickListener {
                listener.onClick(item.id)
            }
        }
    }
}
