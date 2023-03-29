package com.ru.alferatz.repository

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ru.alferatz.model.request.AuthRequest
import com.ru.alferatz.model.response.AggregateBonusByUserResponse
import com.ru.alferatz.model.response.AuthResponse
import com.ru.alferatz.network.AnalyticsService
import com.ru.alferatz.network.ApiClient
import com.ru.alferatz.network.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnalyticsRepository {
    private var analyticsService: AnalyticsService =
        ApiClient.getRetrofit().create(AnalyticsService::class.java)

    fun aggregateByUser(): LiveData<AggregateBonusByUserResponse> {
        val data: MutableLiveData<AggregateBonusByUserResponse> = MutableLiveData()
        analyticsService.aggregateByUser().enqueue(object : Callback<AggregateBonusByUserResponse> {
            override fun onFailure(@NonNull call: Call<AggregateBonusByUserResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                @NonNull call: Call<AggregateBonusByUserResponse>, @NonNull response: Response<AggregateBonusByUserResponse>
            ) {
                data.value = response.body()
            }
        })
        return data
    }

}