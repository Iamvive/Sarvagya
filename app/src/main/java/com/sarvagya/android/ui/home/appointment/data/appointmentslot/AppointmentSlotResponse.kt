package com.sarvagya.android.ui.home.appointment.data.appointmentslot

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentSlotResponse(
    @SerialName("msg") val msg: String? = null,
    @SerialName("available_slots") val slots: List<TimeSlots>? = null,
)

@Serializable
data class TimeSlots(
    @SerialName("slot") val slot: String,
)
