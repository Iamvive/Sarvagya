package com.sarvagya.android.ui.home.feeds.data.models

import com.sarvagya.android.ui.home.feeds.view.FeedsVM
import com.sarvagya.android.ui.home.feeds.view.FeedVM
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedsResponse(
    @SerialName("message")
    val message: String,
    @SerialName("responseCode")
    val response: Int,
    @SerialName("data")
    val feeds: List<Feed>,
)

fun FeedsResponse.toVM() = FeedsVM(feeds = getFeeds(this))

private fun getFeeds(feedsResponse: FeedsResponse): List<FeedVM> =
    feedsResponse.feeds.map {
        it.toVM()
    }

private fun Feed.toVM() = FeedVM(
    id = id.toString(),
    title = title,
    descShort = descShort,
    desc = desc,
    thumbnail = thumbnail,
    language = language,
)

