package com.ru.alferatz.model.entity

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import com.ru.alferatz.enums.BookingStatus
import java.time.LocalDateTime

class BookingEntity {
    @SerializedName("id")
    var id: Long = 0L

    @SerializedName("client_id")
    var clintId: Long = 0L

    @SerializedName("table_id")
    var tableId: Long = 0L

    @RequiresApi(Build.VERSION_CODES.O)
    @SerializedName("time_from")
    var time_from: LocalDateTime = LocalDateTime.now()

    @RequiresApi(Build.VERSION_CODES.O)
    @SerializedName("time_to")
    var time_to: LocalDateTime = LocalDateTime.now()

    @SerializedName("participants")
    var participants: Long = 0L

    @SerializedName("status")
    var status: BookingStatus = BookingStatus.CREATED
}