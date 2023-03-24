package com.ru.alferatz.network

import com.ru.alferatz.model.request.AvailableTablesRequest
import com.ru.alferatz.model.response.TableEntityResponse
import com.ru.alferatz.model.response.TableWithSlotsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDate

interface TableService {
    @GET("table/all")
    fun getAllTables(): Call<TableEntityResponse>

    @GET("table/available")
    fun getAvailableTables(
        @Query("capacity") capacity: Long,
        @Query("date") date: LocalDate
    ): Call<TableWithSlotsResponse>

}