package com.sarvagya.android.ui.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import coil.api.load
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.sarvagya.android.R
import com.sarvagya.android.databinding.FragmentMusicBinding
import com.sarvagya.android.extension.loadImage
import com.sarvagya.android.extension.showToast
import com.sarvagya.android.ui.home.HomeActivity
import com.sarvagya.android.ui.music.data.MusicDataSource
import com.sarvagya.android.ui.music.data.staticmodel.MusicPlaylist
import com.sarvagya.android.ui.music.data.staticmodel.toMediaItem
import com.sarvagya.android.ui.music.view.AlbumAdapter
import com.sarvagya.android.ui.music.view.SongsAdapter

class MusicFragment(private val activity: HomeActivity) : Fragment() {

    private val musicList = MusicDataSource.getMusicPlaylist()
    private val albumAdapter by lazy { AlbumAdapter(musicList) }
    private val songsAdapter by lazy { SongsAdapter(musicList) }
    private var currentPosition = 0

    private lateinit var binding: FragmentMusicBinding
    private lateinit var player: ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.hideNavigation()
        renderView()
    }

    private fun renderView() {
        setAlbumList()
        setSongList()
        initMusicPlayerComponent()
    }

    private fun setAlbumList() {
        binding.albumLyt.albumList.apply {
            layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            adapter = albumAdapter
        }
    }

    private fun setSongList() {
        binding.songsList.apply {
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
            adapter = songsAdapter
        }
    }

    private fun initMusicPlayerComponent() {
        initMusicPlayer()
        registerMusicPlayerListener()
        setMusicUiData(currentPosition)
    }

    private fun registerMusicPlayerListener() {
        binding.musicPlayer.apply {
            btnPlayPauseIV.setOnClickListener {
                onPlayPauseTapped()
            }
            btnNextIV.setOnClickListener {
                onNextTapped()
            }
        }
    }

    private fun setMusicUiData(pos: Int) {
        if (pos > musicList.lastIndex) return

        val item = musicList[pos]
        binding.musicPlayer.apply {
            musicIV.loadImage(item.playlistImage)
            musicTitleTV.text = item.musicName
            singerNameTV.text = item.artistName
        }
    }

    override fun onStart() {
        super.onStart()
        initMusicPlayer()
    }

    private fun initMusicPlayer() {
        player = buildPlayer()
        player.apply {
            setMediaItems(getMediaItems(musicList))
            prepare()
        }
    }

    private fun getMediaItems(musicList: List<MusicPlaylist>) =
        musicList.map { it.toMediaItem() }

    private fun buildPlayer() = ExoPlayer.Builder(requireContext()).build()

    private fun getMediaItem(pos: Int): MediaItem {
        val item = musicList[pos]
        return MediaItem.fromUri(item.musicUrl)
    }

    private fun onPlayPauseTapped() {
        if (player.isPlaying) {
            updatePauseState()
        } else {
            updatePlayState()
        }
    }

    private fun updatePauseState() {
        player.pause()
        binding.musicPlayer.apply {
            btnPlayPauseIV.load(R.drawable.ic_play_arrow_24)
        }
    }

    private fun updatePlayState() {
        player.play()
        binding.musicPlayer.apply {
            btnPlayPauseIV.load(R.drawable.ic_pause_24)
        }
    }

    private fun onNextTapped() {
        if (currentPosition > musicList.lastIndex) {
            requireActivity().showToast("This is last song")
            return
        }

        updatePlayer(currentPosition++)
        setMusicUiData(currentPosition++)
    }

    private fun updatePlayer(currentPosition: Int) {
        player.apply {
            setMediaItem(getMediaItem(currentPosition))
            prepare()
            updatePlayState()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}


