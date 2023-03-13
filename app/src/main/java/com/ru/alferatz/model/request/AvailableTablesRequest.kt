package com.ru.alferatz.model.request

import java.time.LocalDate

data class AvailableTablesRequest(
    // Дата, на которую искать столики
    val date: LocalDate,
    // Необходимая минимальная вместимость столика
    val capacity: Long,
)