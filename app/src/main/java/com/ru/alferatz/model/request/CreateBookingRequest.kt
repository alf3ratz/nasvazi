package com.ru.alferatz.model.request

import java.time.LocalDateTime

data class CreateBookingRequest(
    val name: String,
    val phone: String,
    val participants: Long,
    val tableName: String,
    val timeFrom: LocalDateTime,
    val comment: String?
)