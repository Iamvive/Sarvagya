package com.sarvagya.android.ui.music.data

import com.sarvagya.android.ui.music.data.staticmodel.MusicPlaylist

object MusicDataSource {

    private val imageList = listOf(
        "https://sarvagya.blob.core.windows.net/images/5ac6195f-3cb6-4367-9984-095387c01c23.png",
        "https://sarvagya.blob.core.windows.net/images/maxresdefault.jpg",
        "https://sarvagya.blob.core.windows.net/images/thumb.jpg",
        "https://sarvagya.blob.core.windows.net/images/maxresdefault1.jpg",
    )

    private val nameList = listOf(
        "Ganesh Ji",
        "Hanuman Ji",
        "Sri Krishna Ji",
        "Shiv Ji",
    )

    fun getMusicPlaylist(): List<MusicPlaylist> {
        val mutableList = mutableListOf<MusicPlaylist>()
        for (i in imageList.indices) {
            mutableList.add(getAlbum(name = nameList[i], image = imageList[i]))
        }
        return mutableList
    }

    private fun getAlbum(name: String, image: String) = MusicPlaylist(
        playlistName = name,
        playlistImage = image,
        artistName = "Anu Malik",
        musicUrl = "https://sarvagya.blob.core.windows.net/audios/sample-audio.mp3",
        musicName = "$name Ki Aarti",
    )
}
