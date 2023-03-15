package com.ru.alferatz.network

import com.ru.alferatz.model.request.BookingRequest
import com.ru.alferatz.model.request.CreateBookingRequest
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.model.response.CreateBookingResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookingService {

    @POST("booking/create")
    fun createBooking(@Body request: CreateBookingRequest): Call<CreateBookingResponse>

    @POST("booking/cancel")
    fun cancelBooking(): Call<BookingResponse>

    @POST("booking/confirm")
    fun confirmBooking(): Call<BookingResponse>


    @POST("table/available")
    fun getAvailableTables(): Call<BookingResponse>

    @GET("")
    fun getCurrentFreeTables(): Call<BookingResponse>

    @GET("booking/by-date?date={date}")
    fun getBookingByDate(@Path("date") date: String): Call<BookingResponse>
    @GET("booking/by-date?dateTime={dateTime}")
    fun getBookingByDateTime(@Path("dateTime") dateTime: String): Call<BookingResponse>


    @GET("")
    fun getCurrentBooking(@Path("phoneNumber") phoneNumber: String): Call<BookingResponse>

}