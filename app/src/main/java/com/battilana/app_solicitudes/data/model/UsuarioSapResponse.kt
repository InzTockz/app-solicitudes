package com.battilana.app_solicitudes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioSapResponse (
    val idUsuarioSap: Long,
    val nombreUsuario: String
)