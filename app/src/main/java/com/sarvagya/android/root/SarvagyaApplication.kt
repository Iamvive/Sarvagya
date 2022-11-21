package com.sarvagya.android.root

import android.app.Application
import com.sarvagya.android.ui.home.dagger.AppComponent
import com.sarvagya.android.ui.home.dagger.DaggerAppComponent

class SarvagyaApplication : Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}