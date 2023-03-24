package com.ru.alferatz.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ru.alferatz.model.request.CancelBookingRequest
import com.ru.alferatz.model.request.CreateBookingRequest
import com.ru.alferatz.model.response.CancelBookingResponse
import com.ru.alferatz.model.response.CreateBookingResponse
import com.ru.alferatz.repository.BookingRepository

class SelectedCurrentBookingViewModel(@NonNull application: Application) :
    AndroidViewModel(application) {
    private var bookingRepository: BookingRepository =
        BookingRepository()

    fun cancelBooking(request: CancelBookingRequest): LiveData<CancelBookingResponse> {
        return bookingRepository.cancelBooking(request)
    }
}