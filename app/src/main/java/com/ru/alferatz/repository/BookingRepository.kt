package com.ru.alferatz.repository

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.network.ApiClient
import com.ru.alferatz.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingRepository {
    private var apiService: ApiService = ApiClient.getRetrofit().create(ApiService::class.java)

    fun getCurrentFreeTables(city: String, apiKey: String): LiveData<BookingResponse> {
        val data: MutableLiveData<BookingResponse> = MutableLiveData()
        apiService.getCurrentFreeTables().enqueue(object : Callback<BookingResponse> {
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