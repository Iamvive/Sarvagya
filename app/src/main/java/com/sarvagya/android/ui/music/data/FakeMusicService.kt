package com.sarvagya.android.ui.music.data

import com.sarvagya.android.ui.music.data.MusicDataSource.getPlayListResponse
import com.sarvagya.android.ui.music.data.fav.FavRequest
import com.sarvagya.android.ui.music.data.fav.FavResponse
import com.sarvagya.android.ui.music.data.playlist.PlaylistResponse
import com.sarvagya.android.ui.music.data.songs.SongsRequest
import com.sarvagya.android.ui.music.data.songs.SongsResponse
import javax.inject.Inject

class FakeMusicService
@Inject constructor() : MusicService {
    override suspend fun fetchPlayList(): PlaylistResponse {
        return getPlayListResponse()
    }

    override fun fetchSongs(req: SongsRequest): SongsResponse {
        TODO("Not yet implemented")
    }

    override fun favorite(req: FavRequest): FavResponse {
        TODO("Not yet implemented")
    }

}