package com.ru.alferatz

import android.os.Build
import androidx.annotation.RequiresApi
import com.ru.alferatz.databinding.ActivityMainBinding
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
fun findTableNameById(tableId: Long): String {
    return allTableEntityList.find { i -> i.id == tableId }!!.name
}

fun convertMonthToString(monthNumber: Long): String {
    return when (monthNumber) {
        1L -> "Января"
        2L -> "Февраля"
        3L -> "Марта"
        4L -> "Апреля"
        5L -> "Мая"
        6L -> "Июня"
        7L -> "Июля"
        8L -> "Августа"
        9L -> "Сентября"
        10L -> "Октября"
        11L -> "Ноября"
        12L -> "Декабря"
        else -> {
            "Января"
        }
    }
}

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

lateinit var activityBinding: ActivityMainBinding