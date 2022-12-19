package com.sarvagya.android.ui.music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sarvagya.android.R
import com.sarvagya.android.databinding.FragmentDonationBinding
import com.sarvagya.android.databinding.FragmentMusicBinding
import com.sarvagya.android.ui.home.feeds.FeedsAdapter
import com.sarvagya.android.ui.music.data.playlist.Playlist
import com.sarvagya.android.ui.music.data.staticmodel.MusicPlaylist
import com.sarvagya.android.ui.music.view.AlbumAdapter
import com.sarvagya.android.ui.music.view.MusicPresenter
import com.sarvagya.android.ui.music.view.SongsAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MusicFragment : Fragment() , MusicPresenter {


    private lateinit var binding: FragmentMusicBinding
    val list = listOf(
        getMusicList(),
        getMusicList(),
        getMusicList(),
        getMusicList(),
        getMusicList(),
        getMusicList()
    )
    private val albumAdapter by lazy { AlbumAdapter(list) }
    private val songAdapter by lazy { SongsAdapter(list) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding = FragmentMusicBinding.inflate(layoutInflater)
        setAlbumList()
        setSongList()
        return binding.root
    }

    private fun getMusicList() =  MusicPlaylist(playlistName = "Ganesh Ji",
        playlistImage =  "https://sarvagya.blob.core.windows.net/images/5ac6195f-3cb6-4367-9984-095387c01c23.png",
        artistName = "Anu Mallik",
        musicUrl = "https://sarvagya.blob.core.windows.net/audios/sample-audio.mp3",
        musicName = "Ganesh Ji Ki Arati",
    )

    private fun setAlbumList(){
        binding.albumLyt.albumList .apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = albumAdapter
        }
    }

    private fun setSongList(){
        binding.songsList .apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = songAdapter
        }
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
