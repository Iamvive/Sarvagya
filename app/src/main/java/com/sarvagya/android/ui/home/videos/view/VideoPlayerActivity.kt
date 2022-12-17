package com.sarvagya.android.ui.home.videos.view

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProviders
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.ui.AspectRatioFrameLayout
import com.sarvagya.android.databinding.ActivityVideoPlayerBinding
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.ui.home.videos.VideosViewModel
import com.sarvagya.android.ui.home.videos.VideosViewModelFactory
import javax.inject.Inject

class VideoPlayerActivity : AppCompatActivity() {

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityVideoPlayerBinding.inflate(layoutInflater)
    }
    private val playbackStateListener: Player.Listener = playbackStateListener()
    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L
    @Inject lateinit var videosViewModelFactory : VideosViewModelFactory
    private lateinit var viewModel: VideosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as SarvagyaApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewModel = ViewModelProviders.of(this, videosViewModelFactory)[VideosViewModel::class.java]
        val videoId = intent?.getBundleExtra(VIDEO_DATA)?.let { it.getInt(VIDEO_ID) }

        if (videoId!=null) viewModel.getVideo(videoId)
        observeData()
    }

    private fun observeData() {
        viewModel.liveVideo.observe(this) {
            hideSystemUi()
            if (Util.SDK_INT <= 23 || player == null) {
                initializePlayer(it.videoUrl)
            }
        }
    }

    override fun onStart() {
        super.onStart()
//        if (Util.SDK_INT > 23) {
//            initializePlayer()
//        }
    }

    override fun onResume() {
        super.onResume()

//        hideSystemUi()
//        if (Util.SDK_INT <= 23 || player == null) {
//            initializePlayer()
//        }
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
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, viewBinding.videoView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun initializePlayer(uri: String = URL) {
        val trackSelector = DefaultTrackSelector(applicationContext).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        player = ExoPlayer.Builder(applicationContext)
            .setTrackSelector(trackSelector)
            .build()
            .also { exoPlayer ->
                viewBinding.videoView.apply {
                    player = exoPlayer
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }

                val mediaItem = MediaItem.fromUri(Uri.parse(URL))
                exoPlayer.setMediaItem(mediaItem)

                exoPlayer.playWhenReady = playWhenReady
                Log.i(
                    "VideoPlayerActivity",
                    "initializePlayer: playbackPosition: $playbackPosition"
                )
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.addListener(playbackStateListener)
                exoPlayer.prepare()
            }
    }

    private fun playbackStateListener() = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val stateString: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
                else -> "UNKNOWN_STATE             -"
            }
            Log.d("VideoPlayerActivity", "changed state to $stateString")
        }
    }

    companion object {
        const val VIDEO_ID = "video_id"
        const val VIDEO_DATA = "video_data"
        const val URL = "https://sarvagya.blob.core.windows.net/videos/sample-video.mp4"
    }

}
