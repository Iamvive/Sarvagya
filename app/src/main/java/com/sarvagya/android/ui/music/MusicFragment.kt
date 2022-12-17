package com.sarvagya.android.ui.music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sarvagya.android.R
import com.sarvagya.android.ui.music.view.MusicPresenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MusicFragment : Fragment() , MusicPresenter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    override fun didTapAlbum() {

    }

    override fun didTapSong() {

    }

    override fun didTapVideoMenu() {

    }

    override fun didTapFavorite(): Flow<Boolean> = flow {

    }

    override fun didTapPlay() {

    }

    override fun didTapPrev() {

    }

    override fun didTapNext() {

    }

    override fun didTapBack() {

    }

}
