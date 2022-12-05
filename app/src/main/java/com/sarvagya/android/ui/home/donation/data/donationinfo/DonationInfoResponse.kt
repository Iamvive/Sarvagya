package com.sarvagya.android.ui.home.donation.data.donationinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DonationInfoResponse(
    @SerialName("account_number") val accountNumber: Long,
    @SerialName("bank_name") val bankName: String,
    @SerialName("upi_id") val upiId: String,
)
