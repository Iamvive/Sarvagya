package com.sarvagya.android.root

import android.app.Application
import com.sarvagya.android.root.dagger.AppComponent
import com.sarvagya.android.root.dagger.AppModule
import com.sarvagya.android.root.dagger.DaggerAppComponent
import javax.inject.Inject

class SarvagyaApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
