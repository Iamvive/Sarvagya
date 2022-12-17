package com.sarvagya.android.ui.home.videos.data.http

import com.sarvagya.android.ui.home.videos.data.models.Video
import com.sarvagya.android.ui.home.videos.data.models.VideoResponse
import com.sarvagya.android.ui.home.videos.data.models.VideosResponse

interface VideoService {

    suspend fun fetchVideos() : VideosResponse
    suspend fun getVideo(id : Int) : VideoResponse

}