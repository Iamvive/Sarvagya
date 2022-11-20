package com.sarvagya.android.ui.home.feeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarvagya.android.ui.home.ktor.data.FeedsResponse
import com.sarvagya.android.ui.home.ktor.services.PostsService
import com.sarvagya.android.ui.home.ktor.services.PostsServiceImpl
import io.ktor.client.*
import kotlinx.coroutines.launch

class FeedsViewModel : ViewModel() {

    private val mutablePosts = MutableLiveData<List<FeedsResponse>>()

    val livePost: LiveData<List<FeedsResponse>>
        get() = mutablePosts

    init {
        val service: PostsService = PostsServiceImpl(HttpClient())
        fetchPosts(service)
    }

    private fun fetchPosts(service: PostsService) {
        viewModelScope.launch {
            val posts = service.getPosts()
            mutablePosts.postValue(posts)
        }
    }
}