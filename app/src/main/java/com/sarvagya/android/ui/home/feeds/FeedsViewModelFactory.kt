package com.sarvagya.android.ui.home.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sarvagya.android.ui.home.ktor.services.PostsService
import javax.inject.Inject

class FeedsViewModelFactory
@Inject
constructor(private val feedsService: PostsService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FeedsViewModel(feedsService) as T
    }
}