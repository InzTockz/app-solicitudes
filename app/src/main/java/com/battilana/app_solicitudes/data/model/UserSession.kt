package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserSession (
    val idUsuario: Long,
    val codigo: Int?,
//    val almacen:String?,
    val token:String,
    val status:String,
    val expiresAt: Long? = null
)

fun UserSession.isExpired(now: Long = System.currentTimeMillis()): Boolean{
    return expiresAt?.let { now >= it } ?: true
}