package com.ru.alferatz.model.dto

import java.time.LocalDateTime

data class TableDto(
    val name: String,
    val capacity: Long,
    var availableStartTimes: List<LongArray>
)