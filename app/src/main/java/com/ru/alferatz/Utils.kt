package com.ru.alferatz

import android.net.LocalSocket
import android.os.Build
import androidx.annotation.RequiresApi
import com.ru.alferatz.model.dto.BookingDto
import java.time.LocalDate

var userPhoneNumber = "89164765968"
var selectedTime = ""
var selectedDate = ""
@RequiresApi(Build.VERSION_CODES.O)
var selectedDateAsLocalDate = LocalDate.now()
var bookingListByDateUtils: ArrayList<BookingDto> = ArrayList()
var bookingListByDateTimeUtils: ArrayList<BookingDto> = ArrayList()