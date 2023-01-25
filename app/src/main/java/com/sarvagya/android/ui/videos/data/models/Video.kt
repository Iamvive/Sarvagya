package com.sarvagya.android.ui.videos.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
        @SerialName("id")
        val id: Int,
        @SerialName("title")
        val title: String,
        @SerialName("description")
        val desc: String,
        @SerialName("filePath")
        val videoUrl: String,
        @SerialName("thumbnailPath")
        val thumbnail: String,
        @SerialName("language")
        val language: String,
        @SerialName("relativeDateTime")
        val duration: String,
)
