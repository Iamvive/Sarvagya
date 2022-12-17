package com.sarvagya.android.ui.music.data.songs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongsRequest(
    @SerialName("playlist_id") val id: String,
    @SerialName("count") val count: Int? = null,
)
