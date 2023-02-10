package com.ru.alferatz.enum

enum class BookingStatus(val description: String) {
    CREATED("Создано"),
    CONFIRMED("Подтверждено"),
    CANCELLED("Закрыто")
}