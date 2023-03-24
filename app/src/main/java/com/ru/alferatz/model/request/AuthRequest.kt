package com.ru.alferatz.model.request

data class AuthRequest(
    val phone: String,
    val expectedRole: String = "USER",
    val name: String,
    val chatId: Long
)