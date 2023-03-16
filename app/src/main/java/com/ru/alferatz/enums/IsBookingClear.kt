package com.ru.alferatz.enums

enum class IsBookingClear(val description: String) {
    IS_CLEAR("Свободно"),
    PARTIALLY_NOT_CLEAR("Есть бронирования"),
    FULLY_NOT_CLEAR("Полностью забронировано")
}