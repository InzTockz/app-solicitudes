package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserSession (
    val idUsuario: Long,
    val codigo: Int?,
    val almacen:String?,
    val token:String,
    val status:String
)