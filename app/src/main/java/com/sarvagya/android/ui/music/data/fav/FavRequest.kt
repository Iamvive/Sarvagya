package com.sarvagya.android.ui.music.data.fav

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavRequest(
    @SerialName("song_id") val id: String,
    @SerialName("isFavorite") val favorite: Boolean,
)