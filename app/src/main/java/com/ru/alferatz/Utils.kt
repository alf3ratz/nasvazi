package com.ru.alferatz

import android.os.Build
import androidx.annotation.RequiresApi
import com.ru.alferatz.model.dto.BookingDto
import com.ru.alferatz.model.entity.TableEntity
import java.time.LocalDate

var userPhoneNumber = "89164765968"
var selectedTime = ""
var selectedDate = ""

@RequiresApi(Build.VERSION_CODES.O)
var selectedDateAsLocalDate = LocalDate.now()
var bookingListByDateUtils: List<BookingDto> = ArrayList()
var bookingListByDateTimeUtils: ArrayList<BookingDto> = ArrayList()
var currentUserId = 0L
var allTableEntityList: ArrayList<TableEntity> = ArrayList()
val listOfTableImages = listOf(
    R.drawable.table_1,
    R.drawable.table_2,
    R.drawable.table_3,
    R.drawable.table_4,
    R.drawable.table_5,
    R.drawable.table_7,
    R.drawable.table_8,
    R.drawable.table_9
)