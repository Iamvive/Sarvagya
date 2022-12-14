package com.sarvagya.android.root.dagger

import com.sarvagya.android.ui.home.feeds.data.http.FeedsService
import com.sarvagya.android.ui.home.feeds.data.http.HttpFeedsService
import com.sarvagya.android.ui.home.ktor.httpclient.KtorNetworkClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun createKtorClient() :HttpClient = KtorNetworkClient().createClient()
}

@Module
abstract class BindModule{
    @Binds
    @Singleton
    abstract fun createFeedsService(feedsService: HttpFeedsService) : FeedsService

}