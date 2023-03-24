package com.ru.alferatz.repository

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ru.alferatz.model.request.AuthRequest
import com.ru.alferatz.model.request.ConfirmAuthRequest
import com.ru.alferatz.model.response.AuthResponse
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.network.ApiClient
import com.ru.alferatz.network.AuthService
import com.ru.alferatz.network.BookingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {
    private var authService: AuthService = ApiClient.getRetrofit().create(AuthService::class.java)
    fun login(request: AuthRequest): LiveData<AuthResponse> {
        val data: MutableLiveData<AuthResponse> = MutableLiveData()
        authService.login(request).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(@NonNull call: Call<AuthResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                @NonNull call: Call<AuthResponse>, @NonNull response: Response<AuthResponse>
            ) {
                data.value = response.body()
            }
        })
        return data
    }

    fun confirmAuth(request: ConfirmAuthRequest): LiveData<AuthResponse> {
        val data: MutableLiveData<AuthResponse> = MutableLiveData()
        authService.confirm(request).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(@NonNull call: Call<AuthResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                @NonNull call: Call<AuthResponse>, @NonNull response: Response<AuthResponse>
            ) {
                data.value = response.body()
            }
        })
        return data
    }
}