package com.sarvagya.android.root.dagger

import android.content.Context
import com.sarvagya.android.musicplayer.ExoPlayerImpl
import com.sarvagya.android.musicplayer.MusicPlayer
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.ui.home.feeds.data.http.FeedsService
import com.sarvagya.android.ui.home.feeds.data.http.HttpFeedsService
import com.sarvagya.android.ui.home.ktor.httpclient.KtorNetworkClient
import com.sarvagya.android.ui.home.videos.data.http.HttpVideoService
import com.sarvagya.android.ui.home.videos.data.http.VideoService
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import javax.inject.Singleton

@Module
class AppModule(private val app: SarvagyaApplication) {
    @Provides
    @Singleton
    fun provideApplication() : SarvagyaApplication = app

    @Provides
    @Singleton
    fun appContext() : Context = app.applicationContext

    @Provides
    @Singleton
    fun createKtorClient() :HttpClient = KtorNetworkClient().createClient()
}

@Module
abstract class BindModule{
    @Binds
    @Singleton
    abstract fun createFeedsService(feedsService: HttpFeedsService) : FeedsService


    @Binds
    @Singleton
    abstract fun createVideoService(videosService: HttpVideoService) : VideoService


    @Binds
    @Singleton
    abstract fun createMusicPlayer(ExoPlayerImpl: ExoPlayerImpl) : MusicPlayer
}
