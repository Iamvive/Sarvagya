package com.sarvagya.android.ui.home.feeds.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sarvagya.android.databinding.FragmentFeedsBinding
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.ui.home.feeds.FeedsAdapter
import com.sarvagya.android.ui.home.feeds.FeedsViewModel
import com.sarvagya.android.ui.home.ktor.services.PostsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeedsFragment : Fragment(), FeedsPresenter {

    private lateinit var feedsBinding: FragmentFeedsBinding
    private val feedsAdapter by lazy { FeedsAdapter(listOf()) }
    private lateinit var feedsViewModel: FeedsViewModel
    @Inject lateinit var feedsService : PostsService

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireContext().applicationContext as SarvagyaApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
   }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        feedsBinding = FragmentFeedsBinding.inflate(layoutInflater)
        feedsViewModel = ViewModelProviders.of(requireActivity())[FeedsViewModel::class.java]
        feedsViewModel.fetchPosts(feedsService)
        observeData()
        setUpFeedList()
        return feedsBinding.root
    }

    private fun observeData() {
        feedsViewModel
            .livePost
            .observe(viewLifecycleOwner) { feedsResponses ->
                if (!feedsResponses.isNullOrEmpty()) {
                    feedsResponses.forEach {
                        println("Feeds : ${it.title}")
                    }
                }
            }
    }

    private fun setUpFeedList() {
        feedsBinding.feedsRV.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = feedsAdapter
        }
    }

    override fun didTapBack() {

    }

    override fun didTapItem(): Flow<Long> = feedsAdapter.itemClickFlow
}