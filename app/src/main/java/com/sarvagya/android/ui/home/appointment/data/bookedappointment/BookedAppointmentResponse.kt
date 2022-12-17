package com.sarvagya.android.ui.home.appointment.data.bookedappointment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookedAppointmentResponse(
    @SerialName("booked_appointments") val date: String,
)

data class BookedAppointments(
    @SerialName("date") val date: String,
    @SerialName("time") val time: String,
    @SerialName("status") val status: String,
)
