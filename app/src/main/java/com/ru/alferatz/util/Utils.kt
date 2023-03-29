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
var currentUserId = 35L
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
val listOfInfoLocalImages = arrayListOf(
    R.drawable.info_1,
    R.drawable.info_2,
    R.drawable.info_3,
    R.drawable.info_4,
    R.drawable.info_6,
)

lateinit var activityBinding: ActivityMainBinding

val listOfInfoImages = arrayListOf(
    "https://disk.yandex.ru/i/Ou9VzPJ9q9h2dw",
    "https://disk.yandex.ru/i/buDeqxUe8Z4C8Q",
    "https://disk.yandex.ru/i/k73vXsiml-8a-w",
    "https://disk.yandex.ru/i/zPHcAllABX69Ug",
    "https://disk.yandex.ru/i/8Ko_g5S0Ig8iNA",
    "https://disk.yandex.ru/i/5VZ1Ys-C3eW04A",
    "https://drive.google.com/file/d/1taZKGKxHb1VKGk8_FH418WQZcTxOFAiF/view?usp=share_link"
)