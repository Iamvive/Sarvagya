package com.sarvagya.android.ui.feeds.data.http

import com.sarvagya.android.ui.feeds.data.models.FeedDetailResponse
import com.sarvagya.android.ui.feeds.data.models.FeedsResponse

interface FeedsService {
    suspend fun fetchFeeds(param: String): FeedsResponse
    suspend fun fetchFeedDetail(id: String): FeedDetailResponse
}
