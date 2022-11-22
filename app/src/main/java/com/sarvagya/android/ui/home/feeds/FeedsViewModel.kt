package com.sarvagya.android.ui.home.feeds

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sarvagya.android.ui.home.ktor.data.PostResponse
import com.sarvagya.android.ui.home.ktor.services.PostsService
import com.sarvagya.android.ui.home.ktor.services.PostsServiceImpl
import io.ktor.client.*
import kotlinx.coroutines.launch
import javax.inject.Inject

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