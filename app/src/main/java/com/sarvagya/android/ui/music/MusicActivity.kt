package com.sarvagya.android.ui.music

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import coil.api.load
import com.sarvagya.android.R
import com.sarvagya.android.databinding.ActivityMusicBinding
import com.sarvagya.android.extension.attachWithReplace
import com.sarvagya.android.extension.loadImage
import com.sarvagya.android.extension.showToast
import com.sarvagya.android.musicplayer.MusicPlayer
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.ui.music.data.MusicDataSource
import com.sarvagya.android.ui.music.data.playlist.Playlist
import com.sarvagya.android.ui.music.view.AlbumAdapter
import com.sarvagya.android.ui.music.view.SongsAdapter
import com.sarvagya.android.util.StringProvider
import kotlinx.coroutines.launch
import javax.inject.Inject


//Todo 1. App bar customisation(Color, Text with Icon and Menu).
// 2. Listener for loading Videos Fragment
// 3. On tap of songs play the song
// 4. Selected album highlight
// 5. Highlight the current playing song in the list
class MusicActivity : AppCompatActivity() {

    @Inject
    lateinit var player: MusicPlayer
    @Inject
    lateinit var stringProvider: StringProvider
    private val musicList = MusicDataSource.getMusicPlaylist()
    private val albumAdapter by lazy { AlbumAdapter() }
    private val songsAdapter by lazy { SongsAdapter(musicList) }
    private var currentPosition = 0

    private lateinit var viewModel: MusicViewModel

    @Inject
    lateinit var viewModelFactory: MusicViewModelFactory
    private val binding by lazy {
        ActivityMusicBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as SarvagyaApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //viewModel
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[MusicViewModel::class.java]

        observeData()
        setAlbumList()
        setSongList()
        registerMusicPlayerListener()
        setMusicUiData(currentPosition)

    }

    private fun observeData() {
        viewModel.livePlaylist.observe(this){
            if(it.isNullOrEmpty()) return@observe
            renderPlayListData(it)
        }
    }

    private fun renderPlayListData(playlist: List<Playlist>) {
        albumAdapter.setData(playlist)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.music_toolbar, menu)
        binding.toolbar.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item!!)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_video -> {
                Toast.makeText(this@MusicActivity,R.string.message,Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setAlbumList() {
        binding.albumLyt.albumList.apply {
            layoutManager = LinearLayoutManager(this@MusicActivity, HORIZONTAL, false)
            adapter = albumAdapter
        }
    }

    private fun setSongList() {
        binding.songsList.apply {
            layoutManager = LinearLayoutManager(this@MusicActivity, VERTICAL, false)
            adapter = songsAdapter
        }
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

    private fun onPlayPauseTapped() {
        if (player.isPlaying()) {
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
            this.showToast(stringProvider.invoke(R.string.msg_last_song))
            return
        }
        updatePlayer(currentPosition++)
        setMusicUiData(currentPosition++)
    }

    private fun updatePlayer(currentPosition: Int) {
        player.next(musicList[currentPosition].musicUrl)
        updatePlayState()
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
        viewModel.fetchPlaylist()   //Album list api call
        initMusicPlayer()
    }

    override fun onResume() {
        super.onResume()
        if (!player.isPlaying()) {
            updatePlayState()
        }
    }

    private fun initMusicPlayer() {
        lifecycleScope.launch {
            player.init(musicList.first().musicUrl)
        }
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }

}