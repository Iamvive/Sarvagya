package com.sarvagya.android.ui.home.appointment.data.appointmentslot

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentSlotRequest(
    @SerialName("appointment_date") val date: String,
)
