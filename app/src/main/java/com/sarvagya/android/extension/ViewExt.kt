package com.sarvagya.android.extension

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

fun TextView.setTextWithVisibility(value: String?) {
    if (value.isNullOrEmpty()) this.visibility = View.GONE
    else {
        this.visibility = View.VISIBLE
        this.text = value
    }
}

fun View.setVisibility() {
    if (this.visibility == View.VISIBLE) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}

fun View.clicks(): Flow<Unit> {
    val channel = BroadcastChannel<Unit>(1)
    Handler(Looper.getMainLooper()).post {
        setOnClickListener {
            channel.trySend(Unit)
        }
    }
    return channel.asFlow()
}