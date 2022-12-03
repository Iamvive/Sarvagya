package com.sarvagya.android.ui.home.feeds

import androidx.lifecycle.*
import com.sarvagya.android.ui.home.ktor.data.PostResponse
import com.sarvagya.android.ui.home.ktor.services.PostsService
import kotlinx.coroutines.launch

class FeedsViewModel(private val feedsService : PostsService) : ViewModel() {

    private val mutablePosts = MutableLiveData<List<PostResponse>>()

    val livePost: LiveData<List<PostResponse>>
        get() = mutablePosts

    fun fetchPosts() {
        viewModelScope.launch {
            val posts = feedsService.getPosts()
            mutablePosts.postValue(posts)
        }
    }

}