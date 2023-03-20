package com.ru.alferatz.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ru.alferatz.model.request.BookingByUserRequest
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.repository.BookingRepository

class CurrentBookingViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private var bookingRepository: BookingRepository =
        BookingRepository()

//    fun getCurrentBooking(phoneNumber: String): LiveData<BookingResponse> {
//        return bookingRepository.getCurrentBooking(phoneNumber)
//    }

    fun getBookingByUser(userId: Long): LiveData<BookingResponse> {
        return bookingRepository.getBookingByDateTime(userId)
    }

}