package com.sarvagya.android.ui.music

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarvagya.android.ui.music.data.MusicService
import com.sarvagya.android.ui.music.data.playlist.Playlist
import kotlinx.coroutines.launch

class MusicViewModel(private val service: MusicService) : ViewModel() {

    private val mutablePlaylist = MutableLiveData<List<Playlist>>()
    val livePlaylist : LiveData<List<Playlist>> = mutablePlaylist

    fun fetchPlaylist() {
        viewModelScope.launch {
           val result =  service.fetchPlayList()
            mutablePlaylist.postValue(result.playLists)
        }
    }

}