package com.ru.alferatz.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ru.alferatz.model.request.BookingByUserRequest
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.model.response.TableEntityResponse
import com.ru.alferatz.repository.BookingRepository
import com.ru.alferatz.repository.TableRepository

class BookingViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private var bookingRepository: BookingRepository =
        BookingRepository()
    private var tableRepository: TableRepository =
        TableRepository()

    fun getBookingByDate(date: String): LiveData<BookingResponse> {
        return bookingRepository.getBookingByDate(date)
    }

    fun getAllTables(): LiveData<TableEntityResponse> {
        var res = tableRepository.getAllTables().value
        return tableRepository.getAllTables()
    }

    fun getBookingByDateTime(dateTime: String): LiveData<BookingResponse> {
        return bookingRepository.getBookingByDateTime(dateTime);
    }
}