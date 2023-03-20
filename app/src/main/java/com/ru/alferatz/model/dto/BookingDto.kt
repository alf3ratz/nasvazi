package com.ru.alferatz.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.ru.alferatz.enums.BookingStatus
import java.time.LocalDateTime

data class BookingDto(
    val id: Long?,
    val userId: Long?,
    val userName: String?,
    val phone: String?,
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    val startTime: LongArray,
    //val startTime: LocalDateTime?,
    val status: BookingStatus?,
    val tableId: Long?,
    val participants: Long?,
    val comment: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookingDto

        if (id != other.id) return false
        if (userId != other.userId) return false
        if (userName != other.userName) return false
        if (phone != other.phone) return false
        if (!startTime.contentEquals(other.startTime)) return false
        if (status != other.status) return false
        if (tableId != other.tableId) return false
        if (participants != other.participants) return false
        if (comment != other.comment) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (userId?.hashCode() ?: 0)
        result = 31 * result + (userName?.hashCode() ?: 0)
        result = 31 * result + (phone?.hashCode() ?: 0)
        result = 31 * result + startTime.contentHashCode()
        result = 31 * result + (status?.hashCode() ?: 0)
        result = 31 * result + (tableId?.hashCode() ?: 0)
        result = 31 * result + (participants?.hashCode() ?: 0)
        result = 31 * result + (comment?.hashCode() ?: 0)
        return result
    }
}