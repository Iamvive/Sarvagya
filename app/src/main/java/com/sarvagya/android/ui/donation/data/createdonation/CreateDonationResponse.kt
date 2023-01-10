package com.sarvagya.android.ui.donation.data.createdonation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateDonationResponse(
    @SerialName("message") val msg: String,
)
