package com.sarvagya.android.ui.home.appointment.data

import com.sarvagya.android.ui.home.appointment.data.appointmentslot.AppointmentSlotRequest
import com.sarvagya.android.ui.home.appointment.data.appointmentslot.AppointmentSlotResponse
import com.sarvagya.android.ui.home.appointment.data.bookedappointment.BookedAppointmentRequest
import com.sarvagya.android.ui.home.appointment.data.bookedappointment.BookedAppointmentResponse

interface AppointmentService {
    fun bookAppointmentSlot(req: AppointmentSlotRequest): AppointmentSlotResponse
    fun bookedAppointments(req: BookedAppointmentRequest): BookedAppointmentResponse
}
