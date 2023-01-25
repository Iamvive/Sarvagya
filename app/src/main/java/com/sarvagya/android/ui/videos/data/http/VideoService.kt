package com.sarvagya.android.ui.videos.data.http

import com.sarvagya.android.ui.videos.data.models.Video
import com.sarvagya.android.ui.videos.data.models.VideoResponse
import com.sarvagya.android.ui.videos.data.models.VideosResponse

interface VideoService {

    suspend fun fetchVideos() : VideosResponse
    suspend fun getVideo(id : Int) : VideoResponse

}