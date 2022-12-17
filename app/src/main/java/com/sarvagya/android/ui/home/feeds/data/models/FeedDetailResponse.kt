package com.sarvagya.android.ui.home.feeds.data.models

import com.sarvagya.android.ui.home.feeds.view.FeedsVM
import com.sarvagya.android.ui.home.feeds.view.FeedVM
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedDetailResponse(
    @SerialName("message")
    val message: String,
    @SerialName("responseCode")
    val response: Int,
    @SerialName("data")
    val feed: FeedDetail,
)


