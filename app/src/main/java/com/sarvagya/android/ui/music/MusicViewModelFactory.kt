package com.sarvagya.android.ui.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sarvagya.android.ui.music.data.MusicService
import javax.inject.Inject

class MusicViewModelFactory
@Inject
constructor(
    private val service: MusicService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MusicViewModel(service) as T
    }
}