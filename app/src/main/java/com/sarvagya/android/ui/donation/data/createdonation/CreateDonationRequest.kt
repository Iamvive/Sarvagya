package com.sarvagya.android.ui.donation.data.createdonation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateDonationRequest(
    @SerialName("id") val id: String,
    @SerialName("amount") val amount: String,
)
