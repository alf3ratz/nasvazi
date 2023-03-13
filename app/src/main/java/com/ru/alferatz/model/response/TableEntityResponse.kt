package com.ru.alferatz.model.response

import com.ru.alferatz.model.entity.TableEntity

data class TableEntityResponse(
    val tables: List<TableEntity>
)