package com.sarvagya.android.ui.home.appointment.data.bookedappointment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookedAppointmentRequest(
    @SerialName("user_id") val userId: String,
    @SerialName("date") val date: String,
)
