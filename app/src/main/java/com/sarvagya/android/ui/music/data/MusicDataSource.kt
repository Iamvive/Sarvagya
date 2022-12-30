package com.sarvagya.android.ui.music.data

import com.sarvagya.android.ui.music.data.staticmodel.MusicPlaylist

object MusicDataSource {
    val uri = "https://sarvagya.blob.core.windows.net/audios/sample-audio.mp3"
    val imagePath = "https://sarvagya.blob.core.windows.net/images/5ac6195f-3cb6-4367-9984-095387c01c23.png"
    fun getMusicPlaylist() = listOf(
        getMusicList(),
        getMusicList1(),
        getMusicList2(),
        getMusicList3(),
        getMusicList(),
        getMusicList1()
    )

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
}
