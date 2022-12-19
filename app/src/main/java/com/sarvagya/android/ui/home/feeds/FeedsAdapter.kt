package com.sarvagya.android.ui.home.feeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sarvagya.android.databinding.ItemNewsLytBinding
import com.sarvagya.android.extension.loadImage
import com.sarvagya.android.ui.home.feeds.FeedsAdapter.FeedsViewHolder
import com.sarvagya.android.ui.home.feeds.view.FeedVM
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow

class FeedsAdapter(private var feeds: List<FeedVM>) : Adapter<FeedsViewHolder>() {

    @OptIn(ObsoleteCoroutinesApi::class)
    private val itemChannel = BroadcastChannel<String>(1)
    val itemClickFlow = itemChannel.asFlow()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        val viewBinding = ItemNewsLytBinding.inflate(LayoutInflater.from(parent.context))
        return FeedsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {
        holder.bindData(feeds[position])
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    fun updateFeeds(feeds: List<FeedVM>) {
        this.feeds = feeds
        notifyDataSetChanged()
    }

    @OptIn(ObsoleteCoroutinesApi::class)
    inner class FeedsViewHolder(private val binding: ItemNewsLytBinding) : ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                //Register Listener here
                itemChannel.trySend(feeds[adapterPosition].id)
            }
        }

        fun bindData(feed: FeedVM) {
            binding.apply {
                newsTV.text = feed.title
                newsDescTV.text = feed.desc
                newsTime.text = feed.duration
                newsIV.loadImage(feed.thumbnail)
            }
        }

    }
}