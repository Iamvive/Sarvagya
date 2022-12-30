package com.sarvagya.android.ui.music.data.staticmodel

import com.google.android.exoplayer2.MediaItem

data class MusicPlaylist(
    val playlistName : String,
    val playlistImage : String,
    val artistName : String,
    val musicUrl : String,
    val musicName : String,
)

fun MusicPlaylist.toMediaItem() = MediaItem.fromUri(musicUrl)
