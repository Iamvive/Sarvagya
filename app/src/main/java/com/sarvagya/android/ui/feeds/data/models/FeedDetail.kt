package com.sarvagya.android.ui.feeds.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedDetail(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("shortDescription")
    val descShort: String?,
    @SerialName("description")
    val desc: String,
    @SerialName("filePath")
    val thumbnail: String,
    @SerialName("language")
    val language: String,
    @SerialName("relativeDateTime")
    val duration: String,
)
