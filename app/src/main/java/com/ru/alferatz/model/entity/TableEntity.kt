package com.ru.alferatz.model.entity

import com.google.gson.annotations.SerializedName

data class TableEntity(
    @SerializedName("id")
    var id: Long = 0L,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("capacity")
    var capacity: Long = 0L
)