package com.ru.alferatz.network

import com.ru.alferatz.model.request.ConfirmAuthRequest
import com.ru.alferatz.model.response.AggregateBonusByUserResponse
import com.ru.alferatz.model.response.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AnalyticsService {
    @GET("analytics/aggregate-bonus-by-user")
    fun aggregateByUser(): Call<AggregateBonusByUserResponse>
}