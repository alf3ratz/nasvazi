package com.ru.alferatz.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ru.alferatz.model.request.AuthRequest
import com.ru.alferatz.model.response.AggregateBonusByUserResponse
import com.ru.alferatz.model.response.AuthResponse
import com.ru.alferatz.repository.AnalyticsRepository
import com.ru.alferatz.repository.AuthRepository

class UserInfoViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private var analyticsRepository: AnalyticsRepository =
        AnalyticsRepository()

    fun aggregateByUser(): LiveData<AggregateBonusByUserResponse> {
        return analyticsRepository.aggregateByUser()
    }
}