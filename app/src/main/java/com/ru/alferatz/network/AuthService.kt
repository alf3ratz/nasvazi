package com.ru.alferatz.network

import com.ru.alferatz.model.request.AuthRequest
import com.ru.alferatz.model.response.AuthResponse
import com.ru.alferatz.model.response.BookingResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    fun login(@Body request: AuthRequest): Call<AuthResponse>
}