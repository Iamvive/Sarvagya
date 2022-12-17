package com.sarvagya.android.ui.home.videos.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class Video(
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
        val language: String
)
