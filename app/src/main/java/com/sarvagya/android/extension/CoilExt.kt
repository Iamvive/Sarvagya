package com.sarvagya.android.extension

import android.widget.ImageView
import coil.api.load

fun ImageView.loadImage(url: String) {
    load(url) {
        scaleType = ImageView.ScaleType.FIT_XY
    }
}