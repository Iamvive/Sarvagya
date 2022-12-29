package com.sarvagya.android.ui.music

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerControlView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sarvagya.android.R
import com.sarvagya.android.databinding.FragmentMusicBinding
import com.sarvagya.android.ui.home.HomeActivity
import com.sarvagya.android.ui.home.videos.view.VideoPlayerActivity
import com.sarvagya.android.ui.music.data.staticmodel.MusicPlaylist
import com.sarvagya.android.ui.music.view.AlbumAdapter
import com.sarvagya.android.ui.music.view.MusicPresenter
import com.sarvagya.android.ui.music.view.SongsAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MusicFragment(private val activity: HomeActivity) : Fragment(), MusicPresenter {

    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L
    private var player: ExoPlayer? = null
    private val playbackStateListener: Player.Listener = playbackStateListener()

    private lateinit var binding: FragmentMusicBinding

    val list = listOf(
        getMusicList(),
        getMusicList1(),
        getMusicList2(),
        getMusicList3(),
        getMusicList(),
        getMusicList1()
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
        hideSystemUi()
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer("https://sarvagya.blob.core.windows.net/audios/sample-audio.mp3")//
        }
        return binding.root
    }

    private fun getMusicList() = MusicPlaylist(
        playlistName = "Ganesh Ji",
        playlistImage = "https://sarvagya.blob.core.windows.net/images/5ac6195f-3cb6-4367-9984-095387c01c23.png",
        artistName = "Anu Mallik",
        musicUrl = "https://sarvagya.blob.core.windows.net/audios/sample-audio.mp3",
        musicName = "Ganesh Ji Ki Arati",
    )//dummy data remove later

    private fun getMusicList1() = MusicPlaylist(
        playlistName = "Hanuman Ji",
        playlistImage = "https://sarvagya.blob.core.windows.net/images/maxresdefault.jpg",
        artistName = "Anu Mallik",
        musicUrl = "https://sarvagya.blob.core.windows.net/audios/sample-audio.mp3",
        musicName = "Ganesh Ji Ki Arati",
    )//dummy data remove later

    private fun getMusicList2() = MusicPlaylist(
        playlistName = "Sri Krishna Ji",
        playlistImage = "https://sarvagya.blob.core.windows.net/images/thumb.jpg",
        artistName = "Anu Mallik",
        musicUrl = "https://sarvagya.blob.core.windows.net/audios/sample-audio.mp3",
        musicName = "Ganesh Ji Ki Arati",
    )//dummy data remove later

    private fun getMusicList3() = MusicPlaylist(
        playlistName = "Sihiv Ji",
        playlistImage = "https://sarvagya.blob.core.windows.net/images/maxresdefault1.jpg",
        artistName = "Anu Mallik",
        musicUrl = "https://sarvagya.blob.core.windows.net/audios/sample-audio.mp3",
        musicName = "Ganesh Ji Ki Arati",
    )//dummy data remove later

    private fun setAlbumList() {
        binding.albumLyt.albumList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = albumAdapter
        }
    }

    private fun setSongList() {
        binding.songsList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = songAdapter
        }
    }

    private fun initializePlayer(uri: String) {
        val trackSelector = DefaultTrackSelector(requireContext().applicationContext).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }

        player = ExoPlayer.Builder(activity)
            .setTrackSelector(trackSelector)
            .build()
            .also { exoPlayer ->
                binding.musicPlayer.apply {
                    player = exoPlayer
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
                val mediaMetaData = MediaMetadata.Builder()
                    .setAlbumTitle("Album name")
                    .build()

                val mediaItem =
                    MediaItem.Builder().setMediaMetadata(mediaMetaData)
                        .setUri(Uri.parse(uri))
                        .build()

                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.addListener(playbackStateListener)
                exoPlayer.prepare()
            }
    }

    override fun onStart() {
        super.onStart()
//        if (Util.SDK_INT > 23) {
//            initializePlayer("https://sarvagya.blob.core.windows.net/audios/sample-audio.mp3")
//        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer("https://sarvagya.blob.core.windows.net/audios/sample-audio.mp3")
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.removeListener(playbackStateListener)
            exoPlayer.release()
        }
        player = null
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
        WindowInsetsControllerCompat(activity.window, binding.musicPlayer).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun playListener() = object : ExoPlayer.AudioOffloadListener {

    }
    private fun playbackStateListener() = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val stateString: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
                ExoPlayer.COMMAND_PLAY_PAUSE -> {
                    Toast.makeText(activity, "Play/Pause", Toast.LENGTH_SHORT).show()
                    "ExoPlayer.COMMAND_PLAY_PAUSE"
                }
                else -> "UNKNOWN_STATE             -"
            }
            Log.d("VideoPlayerActivity", "changed state to $playbackState")
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
