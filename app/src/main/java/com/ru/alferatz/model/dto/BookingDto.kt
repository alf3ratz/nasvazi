package com.ru.alferatz.model.dto

import com.ru.alferatz.enums.BookingStatus
import java.time.LocalDateTime

data class BookingDto(
    val id: Long,
    val userName: String,
    val phone: String,
    val startTime: LocalDateTime,
    val status: BookingStatus,
    val tableId: Long,
    val participants: Long,
    val comment: String?
)