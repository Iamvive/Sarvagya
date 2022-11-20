package com.sarvagya.android.ui.home.feeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sarvagya.android.databinding.ItemFeedLytBinding
import com.sarvagya.android.extension.setTextWithVisibility
import com.sarvagya.android.ui.home.feeds.FeedsAdapter.FeedsViewHolder
import com.sarvagya.android.ui.home.feeds.view.FeedsVM.Feed
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow

class FeedsAdapter(private var feeds: List<Feed>) : Adapter<FeedsViewHolder>() {

    private val itemChannel = BroadcastChannel<Long>(1)
    val itemClickFlow = itemChannel.asFlow()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        val viewBinding = ItemFeedLytBinding.inflate(LayoutInflater.from(parent.context))
        return FeedsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {
        holder.bindData(feeds[position])
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    fun updateFeeds(feeds: List<Feed>) {
        this.feeds = feeds
        notifyDataSetChanged()
    }

    inner class FeedsViewHolder(private val binding: ItemFeedLytBinding) : ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) return@setOnClickListener
                //Register Listener here
                itemChannel.trySend(feeds[adapterPosition].id)
            }
        }

        fun bindData(feed: Feed) {
            binding.apply {
                feedTitleTV.text = feed.title
                feedDescTV.text = feed.desc
                readMoreTV.setTextWithVisibility(feed.readMoreTxt)
                durationTV.setTextWithVisibility(feed.durationTxt)
            }
        }
    }
}