package com.sarvagya.android.root.manager.shake

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.SensorManager
import com.chuckerteam.chucker.api.Chucker
import com.squareup.seismic.ShakeDetector
import javax.inject.Inject

class ShakeManager
@Inject
constructor(private val context: Context) : ShakeDetector.Listener {

    operator fun invoke() {
        val sensorManager = context.getSystemService(SENSOR_SERVICE) as SensorManager
        val shakeDetector = ShakeDetector(this)
        shakeDetector.start(sensorManager, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun hearShake() {
        context.startActivity(Chucker.getLaunchIntent(context))
        println("Device shake..")
    }

}