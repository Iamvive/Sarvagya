package com.sarvagya.android.ui.music.view

import kotlinx.coroutines.flow.Flow

interface MusicPresenter {
    fun didTapAlbum()
    fun didTapSong()
    fun didTapVideoMenu()
    fun didTapFavorite(): Flow<Boolean>
    fun didTapPlay()
    fun didTapPrev()
    fun didTapNext()
    fun didTapBack()
}