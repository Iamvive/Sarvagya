package com.sarvagya.android.ui.home.videos

import androidx.lifecycle.*
import com.sarvagya.android.ui.home.videos.data.http.VideoService
import com.sarvagya.android.ui.home.videos.data.models.Video
import kotlinx.coroutines.launch

class VideosViewModel(
    private val videoService: VideoService,
//    private val presenter: FeedsPresenter,
//    private val listener: FeedsListener,
) : ViewModel() {

    private val mutableVideoList = MutableLiveData<List<Video>>()
    private val mutableVideo = MutableLiveData<Video>()

    val liveVideoList: LiveData<List<Video>> get() = mutableVideoList

    val liveVideo: LiveData<Video> get() = mutableVideo

    fun fetchVideos() {
        viewModelScope.launch {
            try {
                val res = videoService.fetchVideos()
                mutableVideoList.postValue(res.videos)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun getVideo(id: Int) {
        viewModelScope.launch {
            try {
                val res = videoService.getVideo(id)
                mutableVideo.postValue(res.video)
            } catch (e: Exception) {
                e.message
            }
        }
    }

}
