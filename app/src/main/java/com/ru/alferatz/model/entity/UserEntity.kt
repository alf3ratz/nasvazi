package com.ru.alferatz.model.entity

import com.google.gson.annotations.SerializedName

class UserEntity {
    @SerializedName("id")
    var id: Long = 0L

    @SerializedName("name")
    var name: String = ""

    @SerializedName("telephone")
    var telephone: String = ""

    @SerializedName("bonus")
    var bonus: Long = 0L
}