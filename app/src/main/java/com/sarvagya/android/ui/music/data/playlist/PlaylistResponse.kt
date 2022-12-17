package com.sarvagya.android.ui.music.data.playlist

import com.sarvagya.android.ui.music.data.songs.Song
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaylistResponse(
    @SerialName("msg") val msg: String? = null,
    @SerialName("playlists") val playLists: List<Playlist>? = null,
)

@Serializable
data class Playlist(
    @SerialName("playlist_id") val id: String,
    @SerialName("playlist_name") val name: String,
    @SerialName("playlist_image") val image: String,
    @SerialName("default_songs") val songs: List<Song>?,
)
