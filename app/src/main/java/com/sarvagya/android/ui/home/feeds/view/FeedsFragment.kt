package com.sarvagya.android.ui.home.feeds.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sarvagya.android.databinding.FragmentFeedsBinding
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.ui.home.feeds.FeedsAdapter
import com.sarvagya.android.ui.home.feeds.FeedsListener
import com.sarvagya.android.ui.home.feeds.FeedsViewModel
import com.sarvagya.android.ui.home.feeds.FeedsViewModelFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeedsFragment(private val listener: FeedsListener) :
    Fragment(), FeedsPresenter {

    private lateinit var feedsBinding: FragmentFeedsBinding
    private val feedsAdapter by lazy { FeedsAdapter(listOf()) }
    private lateinit var feedsViewModel: FeedsViewModel

    @Inject
    lateinit var feedsViewModelFactory: FeedsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireContext().applicationContext as SarvagyaApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        feedsBinding = FragmentFeedsBinding.inflate(layoutInflater)
        feedsViewModel = ViewModelProviders.of(
            requireActivity(),
            feedsViewModelFactory
        )[FeedsViewModel::class.java]
        feedsViewModel.fetchFeeds()
        feedsViewModel.handlePresenter(this, listener)

        return feedsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setUpFeedList()
    }

    private fun observeData() {
        feedsViewModel
            .livePost
            .observe(viewLifecycleOwner) { feeds ->
                if (!feeds.isNullOrEmpty()) {
                    feedsAdapter.updateFeeds(feeds)
                }
            }
    }

    private fun setUpFeedList() {
        feedsBinding.feedsRV.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = feedsAdapter
        }
    }

    override fun didTapBack() {}

    override fun didTapItem(): Flow<String> = feedsAdapter.itemClickFlow
}
