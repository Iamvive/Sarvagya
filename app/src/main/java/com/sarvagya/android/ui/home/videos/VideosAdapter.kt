package com.sarvagya.android.ui.home.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sarvagya.android.databinding.ItemVideoLayoutBinding

class VideosAdapter(
    private val listener: VideoAdapterOnClickListener,
) : Adapter<VideosAdapter.VideosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val viewBinding = ItemVideoLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return VideosViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 7
    }

    inner class VideosViewHolder(private val binding: ItemVideoLayoutBinding) :
        ViewHolder(binding.root) {
        fun bind() {
            binding.videoTitleTV.text =
                "आज है मार्गशीर्ष माह की विनायक चतुर्थी, जानें शुभ मुहूर्त, पूजन विधि और महत्व"
            binding.durationTV.text = "1 घंटा पहले"
            binding.ivVideo.setOnClickListener {
                listener.onClick()
            }
        }
    }


}