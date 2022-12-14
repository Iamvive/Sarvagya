package com.sarvagya.android.root.dagger

import com.sarvagya.android.ui.home.feeds.FeedDetailActivity
import com.sarvagya.android.ui.home.feeds.data.http.FeedsService
import com.sarvagya.android.ui.home.feeds.view.FeedsFragment
import dagger.Component
import io.ktor.client.*
import javax.inject.Singleton

@Component(modules = [AppModule::class,BindModule::class])
@Singleton
interface AppComponent {

    fun provideKtorClient() : HttpClient

    fun provideFeedsService() : FeedsService

    fun inject(fragment : FeedsFragment)

    fun inject(activity : FeedDetailActivity)

}