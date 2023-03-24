package com.ru.alferatz.repository

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ru.alferatz.model.request.AvailableTablesRequest
import com.ru.alferatz.model.response.TableEntityResponse
import com.ru.alferatz.model.response.TableWithSlotsResponse
import com.ru.alferatz.network.ApiClient
import com.ru.alferatz.network.TableService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class TableRepository {
    private var tableService: TableService =
        ApiClient.getRetrofit().create(TableService::class.java)

    fun getAllTables(): LiveData<TableEntityResponse> {
        val data: MutableLiveData<TableEntityResponse> = MutableLiveData()
        tableService.getAllTables().enqueue(object : Callback<TableEntityResponse> {
            override fun onFailure(@NonNull call: Call<TableEntityResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                @NonNull call: Call<TableEntityResponse>,
                @NonNull response: Response<TableEntityResponse>
            ) {
                data.value = response.body()
            }
        })
        return data
    }

    fun getAvailableTables(capacity:Long, date:LocalDate): LiveData<TableWithSlotsResponse> {
        val data: MutableLiveData<TableWithSlotsResponse> = MutableLiveData()
        tableService.getAvailableTables(capacity, date).enqueue(object : Callback<TableWithSlotsResponse> {
            override fun onFailure(@NonNull call: Call<TableWithSlotsResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                @NonNull call: Call<TableWithSlotsResponse>,
                @NonNull response: Response<TableWithSlotsResponse>
            ) {
                data.value = response.body()
            }
        })
        return data
    }
}