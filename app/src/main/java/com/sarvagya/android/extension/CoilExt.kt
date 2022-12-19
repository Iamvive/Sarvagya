package com.sarvagya.android.extension

import android.widget.ImageView
import coil.api.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation

fun ImageView.loadImage(url: String) {
    load(url) {
        scaleType = ImageView.ScaleType.FIT_XY
    }
}

fun ImageView.loadCircleCropImage(url: String) {
    load(url){
        CircleCropTransformation()
    }
}

fun ImageView.loadRoundCornerImage(url: String, cornerRadius : Float) {
    load(url){
        RoundedCornersTransformation(
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius,
        )
    }
}
