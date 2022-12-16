package com.sarvagya.android.root.dagger

import android.app.Application
import android.content.Context
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.ui.home.HomeActivity
import com.sarvagya.android.ui.home.feeds.FeedDetailActivity
import com.sarvagya.android.ui.home.feeds.data.http.FeedsService
import com.sarvagya.android.ui.home.feeds.view.FeedsFragment
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

    fun inject(fragment : FeedsFragment)

    fun inject(activity : FeedDetailActivity)

    fun inject(activity : HomeActivity)

}