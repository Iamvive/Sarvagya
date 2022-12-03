package com.sarvagya.android.ui.home.feeds.view


    data class FeedsVM(
        val feeds : List<FeedVM>,
    )

    data class FeedVM(
        val id: String,
        val title: String,
        val descShort: String?,
        val desc: String,
        val thumbnail: String,
        val language: String,
    )