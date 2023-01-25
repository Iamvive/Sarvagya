package com.sarvagya.android.root.dagger

import android.content.Context
import com.sarvagya.android.musicplayer.ExoPlayerImpl
import com.sarvagya.android.musicplayer.MusicPlayer
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.ui.feeds.data.http.FeedsService
import com.sarvagya.android.ui.feeds.data.http.HttpFeedsService
import com.sarvagya.android.root.ktor.httpclient.KtorNetworkClient
import com.sarvagya.android.root.manager.shake.ShakeManager
import com.sarvagya.android.ui.music.data.FakeMusicService
import com.sarvagya.android.ui.music.data.MusicService
import com.sarvagya.android.ui.videos.data.http.HttpVideoService
import com.sarvagya.android.ui.videos.data.http.VideoService
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class AppModule(private val app: SarvagyaApplication) {
    @Provides
    @Singleton
    fun provideApplication(): SarvagyaApplication = app

    @Provides
    @Singleton
    fun appContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun createKtorClient(context: Context): HttpClient = KtorNetworkClient(context).createClient()

    @Provides
    @Singleton
    fun okhttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun provideShakeManager(context: Context): ShakeManager = ShakeManager(context)

}

@Module
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun createFeedsService(feedsService: HttpFeedsService): FeedsService


    @Binds
    @Singleton
    abstract fun createVideoService(videosService: HttpVideoService): VideoService


    @Binds
    @Singleton
    abstract fun createMusicPlayer(exoPlayerImpl: ExoPlayerImpl): MusicPlayer

    @Binds
    @Singleton
    abstract fun createMusicService(service: FakeMusicService): MusicService
}
