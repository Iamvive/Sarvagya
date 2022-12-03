package com.sarvagya.android.ui.home.feeds

import androidx.lifecycle.*
import com.sarvagya.android.ui.home.feeds.data.http.FeedsService
import com.sarvagya.android.ui.home.feeds.data.models.toVM
import com.sarvagya.android.ui.home.feeds.view.FeedVM
import kotlinx.coroutines.launch

class FeedsViewModel(
    private val feedsService: FeedsService
) : ViewModel() {
    private companion object {
        const val LANGUAGE = "Hindi"
    }

    private val mutablePosts = MutableLiveData<List<FeedVM>>()

    val livePost: LiveData<List<FeedVM>>
        get() = mutablePosts

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

}