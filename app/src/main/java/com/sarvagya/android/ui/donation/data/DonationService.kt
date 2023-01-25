package com.sarvagya.android.ui.donation.data

import com.sarvagya.android.ui.donation.data.createdonation.CreateDonationRequest
import com.sarvagya.android.ui.donation.data.createdonation.CreateDonationResponse
import com.sarvagya.android.ui.donation.data.donationinfo.DonationInfoRequest
import com.sarvagya.android.ui.donation.data.donationinfo.DonationInfoResponse

interface DonationService {
    fun fetchDonationInfoData(req: DonationInfoRequest): DonationInfoResponse
    fun createDonation(req: CreateDonationRequest): CreateDonationResponse
}
