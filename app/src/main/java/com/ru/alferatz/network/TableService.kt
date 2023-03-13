package com.ru.alferatz.network

import com.ru.alferatz.model.request.AvailableTablesRequest
import com.ru.alferatz.model.response.TableEntityResponse
import com.ru.alferatz.model.response.TableWithSlotsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TableService {
    @GET("table/all")
    fun getAllTables(): Call<TableEntityResponse>

    @POST("table/available")
    fun getAvailableTables(@Body request:AvailableTablesRequest):Call<TableWithSlotsResponse>

}