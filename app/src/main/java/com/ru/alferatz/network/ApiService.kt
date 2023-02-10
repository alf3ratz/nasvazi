package com.ru.alferatz.network

import com.ru.alferatz.model.response.BookingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("")
    fun getCurrentFreeTables(): Call<BookingResponse>
}