package com.sarvagya.android.ui.music.data.songs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongsResponse(
    @SerialName("msg") val msg: String?,
    @SerialName("songs") val songs: List<Song>?,
    @SerialName("total_count") val totalSongs: Int,
)

@Serializable
data class Song(
    @SerialName("song_id") val id: String,
    @SerialName("song_name") val name: String,
    @SerialName("song_image") val image: String,
    @SerialName("singer_name") val singerName: String,
    @SerialName("isFavorite") val isFavorite: Boolean,
)