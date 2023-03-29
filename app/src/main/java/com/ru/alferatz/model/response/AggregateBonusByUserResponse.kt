package com.ru.alferatz.model.response

import com.ru.alferatz.model.dto.AnalysisBonusDto

data class AggregateBonusByUserResponse(
    val analysisBonusDtos: List<AnalysisBonusDto>
)