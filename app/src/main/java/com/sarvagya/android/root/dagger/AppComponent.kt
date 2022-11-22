package com.sarvagya.android.root.dagger

import com.sarvagya.android.ui.home.feeds.view.FeedsFragment
import com.sarvagya.android.ui.home.ktor.services.PostsService
import dagger.Component
import io.ktor.client.*
import javax.inject.Singleton

@Component(modules = [AppModule::class,BindModule::class])
@Singleton
interface AppComponent {

    fun provideKtorClient() : HttpClient

    fun provideFeedsService() : PostsService

    fun inject(fragment : FeedsFragment)

}