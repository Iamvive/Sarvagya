package com.sarvagya.android.ui.home.feeds

import androidx.lifecycle.*
import com.sarvagya.android.ui.home.feeds.data.http.FeedsService
import com.sarvagya.android.ui.home.feeds.data.models.Feed
import com.sarvagya.android.ui.home.feeds.data.models.FeedDetail
import com.sarvagya.android.ui.home.feeds.data.models.toVM
import com.sarvagya.android.ui.home.feeds.view.FeedVM
import com.sarvagya.android.ui.home.feeds.view.FeedsPresenter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class FeedsViewModel(
    private val feedsService: FeedsService,
//    private val presenter: FeedsPresenter,
//    private val listener: FeedsListener,
) : ViewModel() {
    private companion object {
        const val LANGUAGE = "Hindi"
    }

    private val mutablePosts = MutableLiveData<List<FeedVM>>()
    private val mutableFeedDetail = MutableLiveData<FeedDetail>()

    val livePost: LiveData<List<FeedVM>> get() = mutablePosts

    val liveFeedDetail: LiveData<FeedDetail> get() = mutableFeedDetail


    fun handlePresenter(presenter: FeedsPresenter, listener: FeedsListener) {
        viewModelScope.launch {
            presenter.didTapItem()
                .distinctUntilChanged()
                .collect {
                    listener.onFeedTapped(it)
                }
        }
    }

    fun fetchFeeds() {
        viewModelScope.launch {
            try {
                val feedResponse = feedsService.fetchFeeds(LANGUAGE).toVM()
                mutablePosts.postValue(feedResponse.feeds)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun fetchFeedDetail(id: String) {
        viewModelScope.launch {
            try {
                val feedResponse = feedsService.fetchFeedDetail(id)
                mutableFeedDetail.postValue(feedResponse.feed)
            } catch (e: Exception) {
                e.message
            }
        }
    }

}