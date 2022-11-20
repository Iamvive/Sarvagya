package com.sarvagya.android.ui.home.feeds.view

class FeedsVM {
    data class Feeds(
        val feeds : List<Feed>,
    )

    data class Feed(
        val id: Long,
        val title: String,
        val desc: String,
        val readMoreTxt: String,
        val durationTxt: String,
        val thumbnailUrl: String,
        val streamingUrl: String,
    )
}