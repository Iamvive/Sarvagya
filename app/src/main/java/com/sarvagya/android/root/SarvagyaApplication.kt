package com.sarvagya.android.root

import android.app.Application
import com.sarvagya.android.root.dagger.AppComponent
import com.sarvagya.android.root.dagger.DaggerAppComponent

class SarvagyaApplication : Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}