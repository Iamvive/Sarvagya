package com.sarvagya.android.ui.home.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sarvagya.android.ui.home.feeds.data.http.FeedsService
import javax.inject.Inject

class FeedsViewModelFactory
@Inject
constructor(private val feedsService: FeedsService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FeedsViewModel(feedsService) as T
    }
}