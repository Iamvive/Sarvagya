package com.sarvagya.android.ui.videos.data.http

import com.sarvagya.android.root.ktor.services.HttpRoutes
import com.sarvagya.android.ui.videos.data.models.VideoResponse
import com.sarvagya.android.ui.videos.data.models.VideosResponse
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class HttpVideoService
@Inject
constructor(private val client: HttpClient) : VideoService {
    override suspend fun fetchVideos(): VideosResponse {
        return client.get{
            url(HttpRoutes.VIDEOS)
        }
    }

    override suspend fun getVideo(id: Int): VideoResponse {
        return client.get {
            url(scheme = "http") {
                host = "3.109.222.122"
                port = 8083
                path("Video/$id")
            }
        }
    }
}