package com.sarvagya.android.musicplayer

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import javax.inject.Inject

interface MusicPlayer {
    suspend fun init(uri: String)
    fun isPlaying(): Boolean
    fun play()
    fun pause()
    fun next(uri: String)
    fun release()
}

class ExoPlayerImpl
@Inject
constructor(
    private val context: Context,
) : MusicPlayer {

    private lateinit var player: ExoPlayer

    override suspend fun init(uri: String) {
        try {
            player = ExoPlayer.Builder(context).build()
            prepareMediaPlayer(uri)
        } catch (e: Exception) {
            e.message
        }
    }

    private fun prepareMediaPlayer(uri: String) {
        player.setMediaItem(getMediaItem(uri))
        player.prepare()
    }

    private fun getMediaItem(uri: String): MediaItem = MediaItem.fromUri(uri)

    override fun isPlaying(): Boolean =
       player.isPlaying

    override fun play() {
        player.play()
    }

    override fun pause() {
        player.pause()
    }

    override fun next(uri: String) {
        prepareMediaPlayer(uri)
        play()
    }

    override fun release() {
        player.stop()
        player.release()
    }

}