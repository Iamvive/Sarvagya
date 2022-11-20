package com.sarvagya.android.ui.home.ktor.data

import kotlinx.serialization.Serializable

@Serializable
data class FeedsResponse(
    val body: String,
    val title: String,
    val id: Int,
    val userId: Int
)
