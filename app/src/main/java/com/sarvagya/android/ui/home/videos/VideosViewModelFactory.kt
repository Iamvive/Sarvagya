package com.sarvagya.android.ui.home.videos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sarvagya.android.ui.home.videos.data.http.VideoService
import javax.inject.Inject

class VideosViewModelFactory
@Inject
constructor(
    private val videoService: VideoService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideosViewModel(videoService) as T
    }
}