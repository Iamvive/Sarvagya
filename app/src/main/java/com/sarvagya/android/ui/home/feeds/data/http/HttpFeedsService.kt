package com.sarvagya.android.ui.home.feeds.data.http

import com.sarvagya.android.ui.home.feeds.data.models.FeedsResponse
import com.sarvagya.android.ui.home.ktor.services.HttpRoutes
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class HttpFeedsService
@Inject constructor(
    private val httpClient: HttpClient,
) : FeedsService {
    override suspend fun fetchFeeds(param: String): FeedsResponse {
        return httpClient.get {
            url(HttpRoutes.FEEDS)
            parameter("language", param)
        }
    }
}