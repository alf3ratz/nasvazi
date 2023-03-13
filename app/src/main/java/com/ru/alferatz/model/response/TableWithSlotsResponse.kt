package com.ru.alferatz.model.response

import com.ru.alferatz.model.dto.TableDto
import com.ru.alferatz.model.entity.TableEntity

data class TableWithSlotsResponse(
    val tables: List<TableDto>
)