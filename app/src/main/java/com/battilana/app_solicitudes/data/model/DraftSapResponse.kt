package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@Serializable
data class DraftSapResponse(
    val docEntry: Int,
    val docNum: Int,
    val cardCode: String,
    val cardName: String,
    val objType: String,
    val docDate: String,
    val docTotal: Double,
    val docStatus: String,
    val canceled: String,
    val slpCode: String,
    val createDate: String,
    val wddStatus:String
)
