package com.sarvagya.android.ui.feeds.data.http

import com.sarvagya.android.ui.feeds.data.models.FeedDetailResponse
import com.sarvagya.android.ui.feeds.data.models.FeedsResponse
import com.sarvagya.android.root.ktor.services.HttpRoutes
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import javax.inject.Inject

class HttpFeedsService
@Inject constructor(
    private val httpClient: HttpClient,
) : FeedsService {

    companion object {
        const val LANGUAGE = "language"
    }

    override suspend fun fetchFeeds(param: String): FeedsResponse {
        return httpClient.get {
            url(HttpRoutes.FEEDS)
            parameter(LANGUAGE, param)
        }
    }

    override suspend fun fetchFeedDetail(id: String): FeedDetailResponse {
        return httpClient.get {
            url(scheme = "http"){
            host = "3.109.222.122"
                port = 8083
                path("NewsFeed/$id")
            }

        }
    }

}
