package com.ru.alferatz

import android.net.LocalSocket
import com.ru.alferatz.model.dto.BookingDto
import java.time.LocalDate

var userPhoneNumber = "89164765968"
var selectedTime = ""
var selectedDate = ""
var selectedDateAsLocalDate = LocalDate.now()
var bookingListByDateUtils: List<BookingDto> = ArrayList()