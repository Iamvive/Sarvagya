package com.sarvagya.android.root.dagger

import android.content.Context
import com.sarvagya.android.musicplayer.MusicPlayer
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.root.manager.shake.ShakeManager
import com.sarvagya.android.ui.home.HomeActivity
import com.sarvagya.android.ui.feeds.FeedDetailActivity
import com.sarvagya.android.ui.feeds.data.http.FeedsService
import com.sarvagya.android.ui.feeds.view.FeedsFragment
import com.sarvagya.android.ui.videos.data.http.VideoService
import com.sarvagya.android.ui.videos.view.VideoPlayerActivity
import com.sarvagya.android.ui.videos.view.VideosFragment
import com.sarvagya.android.ui.music.MusicActivity
import com.sarvagya.android.ui.music.MusicFragment
import com.sarvagya.android.ui.music.data.MusicService
import dagger.Component
import io.ktor.client.*
import javax.inject.Singleton

@Component(modules = [AppModule::class, BindModule::class])
@Singleton
interface AppComponent {

    fun application() : SarvagyaApplication

    fun appContext() : Context

    fun provideKtorClient() : HttpClient

    fun provideFeedsService() : FeedsService

    fun provideVideosService() : VideoService

    fun provideMusicService() : MusicService

    fun provideMusicPlayer() : MusicPlayer

    fun provideShakeManager() : ShakeManager

    fun inject(fragment : FeedsFragment)

    fun inject(activity : FeedDetailActivity)

    fun inject(activity : VideoPlayerActivity)

    fun inject(activity : HomeActivity)

    fun inject(fragment : VideosFragment)

    fun inject(fragment : MusicFragment)

    fun inject(activity : MusicActivity)
}
