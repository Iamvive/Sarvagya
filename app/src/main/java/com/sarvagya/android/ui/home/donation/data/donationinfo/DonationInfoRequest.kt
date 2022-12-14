package com.sarvagya.android.ui.home.donation.data.donationinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DonationInfoRequest(
    @SerialName("id") val id: String,
)
