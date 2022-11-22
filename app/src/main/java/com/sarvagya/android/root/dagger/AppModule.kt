package com.sarvagya.android.root.dagger

import com.sarvagya.android.ui.home.ktor.httpclient.KtorNetworkClient
import com.sarvagya.android.ui.home.ktor.services.PostsService
import com.sarvagya.android.ui.home.ktor.services.PostsServiceImpl
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
    abstract fun createFeedsService(postsServiceImpl: PostsServiceImpl) : PostsService


}