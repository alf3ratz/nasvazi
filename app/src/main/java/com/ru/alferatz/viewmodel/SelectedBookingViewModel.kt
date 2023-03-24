package com.ru.alferatz.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ru.alferatz.model.request.AvailableTablesRequest
import com.ru.alferatz.model.request.CreateBookingRequest
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.model.response.CreateBookingResponse
import com.ru.alferatz.model.response.TableWithSlotsResponse
import com.ru.alferatz.repository.BookingRepository
import com.ru.alferatz.repository.TableRepository
import java.time.LocalDate

class SelectedBookingViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private var bookingRepository: BookingRepository =
        BookingRepository()
    private var tableRepository: TableRepository =
        TableRepository()

    fun createBooking(request: CreateBookingRequest): LiveData<CreateBookingResponse> {
        return bookingRepository.createBooking(request)
    }

    fun getAvailableTables(capacity: Long, date: LocalDate): LiveData<TableWithSlotsResponse> {
        return tableRepository.getAvailableTables(capacity, date)
    }
}