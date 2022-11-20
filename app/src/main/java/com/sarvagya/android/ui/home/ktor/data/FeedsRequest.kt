package com.sarvagya.android.ui.home.ktor.data

import kotlinx.serialization.Serializable

@Serializable
data class FeedsRequest(
    val body: String,
    val title: String,
    val userId: Int
)