package com.sarvagya.android.ui.music.data

import com.sarvagya.android.ui.music.data.fav.FavRequest
import com.sarvagya.android.ui.music.data.fav.FavResponse
import com.sarvagya.android.ui.music.data.playlist.PlaylistResponse
import com.sarvagya.android.ui.music.data.songs.SongsRequest
import com.sarvagya.android.ui.music.data.songs.SongsResponse

interface MusicService {
    suspend fun fetchPlayList(): PlaylistResponse
    fun fetchSongs(req: SongsRequest): SongsResponse
    fun favorite(req: FavRequest): FavResponse
}
