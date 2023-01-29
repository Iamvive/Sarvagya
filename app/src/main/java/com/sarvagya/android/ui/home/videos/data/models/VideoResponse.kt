package com.sarvagya.android.ui.home.videos.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResponse(
    @SerialName("message")
    val message : String,
    @SerialName("responseCode")
    val responseCode : Int,
    @SerialName("data")
    val video : Video,
)