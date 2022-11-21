package com.sarvagya.android.ui.home.dagger

import com.sarvagya.android.ui.home.ktor.httpclient.KtorNetworkClient
import com.sarvagya.android.ui.home.ktor.services.PostsService
import com.sarvagya.android.ui.home.ktor.services.PostsServiceImpl
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun createKtorClient() :HttpClient = KtorNetworkClient().createClient()

    @Provides
    @Singleton
    fun createFeedsService(httpClient: HttpClient) : PostsService = PostsServiceImpl(httpClient)

}