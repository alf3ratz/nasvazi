package com.ru.alferatz.repository

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ru.alferatz.model.request.BookingRequest
import com.ru.alferatz.model.request.CreateBookingRequest
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.model.response.CreateBookingResponse
import com.ru.alferatz.network.ApiClient
import com.ru.alferatz.network.BookingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingRepository {
    private var bookingService: BookingService = ApiClient.getRetrofit().create(BookingService::class.java)

    fun getCurrentFreeTables(city: String, apiKey: String): LiveData<BookingResponse> {
        val data: MutableLiveData<BookingResponse> = MutableLiveData()
        bookingService.getCurrentFreeTables().enqueue(object : Callback<BookingResponse> {
            override fun onFailure(@NonNull call: Call<BookingResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                @NonNull call: Call<BookingResponse>,
                @NonNull response: Response<BookingResponse>
            ) {
                data.value = response.body()
            }
        })
        return data
    }

    fun getCurrentBooking(phoneNumber: String): LiveData<BookingResponse> {
        val data: MutableLiveData<BookingResponse> = MutableLiveData()
        bookingService.getCurrentBooking(phoneNumber).enqueue(object : Callback<BookingResponse> {
            override fun onFailure(@NonNull call: Call<BookingResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                @NonNull call: Call<BookingResponse>,
                @NonNull response: Response<BookingResponse>
            ) {
                data.value = response.body()
            }
        })
        return data
    }

    fun createBooking(createResponse: CreateBookingRequest): LiveData<CreateBookingResponse> {
        val data: MutableLiveData<CreateBookingResponse> = MutableLiveData()
        bookingService.createBooking(createResponse).enqueue(object : Callback<CreateBookingResponse> {
            override fun onFailure(@NonNull call: Call<CreateBookingResponse>, t: Throwable) {
                data.value = null
                var qwe = ""
            }

            override fun onResponse(
                @NonNull call: Call<CreateBookingResponse>,
                @NonNull response: Response<CreateBookingResponse>
            ) {
                data.value = response.body()
                var qwe = ""
            }
        })
        return data
    }

    fun getBookingByDate(date: String): LiveData<BookingResponse> {
        val data: MutableLiveData<BookingResponse> = MutableLiveData()
        bookingService.getBookingByDate(date).enqueue(object : Callback<BookingResponse> {
            override fun onFailure(@NonNull call: Call<BookingResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                @NonNull call: Call<BookingResponse>,
                @NonNull response: Response<BookingResponse>
            ) {
                data.value = response.body()
            }
        })
        return data
    }
    fun getBookingByDateTime(dateTime: String): LiveData<BookingResponse> {
        val data: MutableLiveData<BookingResponse> = MutableLiveData()
        bookingService.getBookingByDateTime(dateTime).enqueue(object : Callback<BookingResponse> {
            override fun onFailure(@NonNull call: Call<BookingResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                @NonNull call: Call<BookingResponse>,
                @NonNull response: Response<BookingResponse>
            ) {
                data.value = response.body()
            }
        })
        return data
    }


}