package com.sarvagya.android.ui.appointment.data

import com.sarvagya.android.ui.appointment.data.appointmentslot.AppointmentSlotRequest
import com.sarvagya.android.ui.appointment.data.appointmentslot.AppointmentSlotResponse
import com.sarvagya.android.ui.appointment.data.bookedappointment.BookedAppointmentRequest
import com.sarvagya.android.ui.appointment.data.bookedappointment.BookedAppointmentResponse

interface AppointmentService {
    fun bookAppointmentSlot(req: AppointmentSlotRequest): AppointmentSlotResponse
    fun bookedAppointments(req: BookedAppointmentRequest): BookedAppointmentResponse
}
