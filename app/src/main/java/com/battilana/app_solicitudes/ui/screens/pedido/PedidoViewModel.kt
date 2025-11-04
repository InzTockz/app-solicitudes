package com.battilana.app_solicitudes.ui.screens.pedido

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battilana.app_solicitudes.data.local.UserPreferences
import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.data.model.UsuarioResponse
import com.battilana.app_solicitudes.data.model.UsuarioSapResponse
import com.battilana.app_solicitudes.domain.usecase.SapUseCase
import com.battilana.app_solicitudes.domain.usecase.UsuarioSapUseCase
import com.battilana.app_solicitudes.navigate.SessionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class PedidoViewModel @Inject constructor(
    private val usuarioSapUseCase: UsuarioSapUseCase,
    private val sapUseCase: SapUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiStateUsuarioSapResponse = MutableStateFlow<List<UsuarioSapResponse>>(emptyList())
    val uiStateUsuarioResponse: StateFlow<List<UsuarioSapResponse>> = _uiStateUsuarioSapResponse

    private val _uiStateClienteSapResponse = MutableStateFlow<List<ClientesSapResponse>>(emptyList())
    val uiStateClienteSapResponse: StateFlow<List<ClientesSapResponse>> = _uiStateClienteSapResponse

    fun cargarUsuariosSap() {
        viewModelScope.launch {
            try {
                val usuarios = usuarioSapUseCase.listarUsuarioSap()
                _uiStateUsuarioSapResponse.value = usuarios

            } catch (e: Exception) {
                Log.i("USUARIO_ERROR", "Error al cargar usuarios: ${e.message}")
            }
        }
    }

    fun cargarClientesSap() {
        viewModelScope.launch {
            try {
                val session = userPreferences.userSession.first()
                val idUsuario = session?.idUsuario?.toInt() ?: return@launch

                val clientes = sapUseCase.listarClientesPorVendedor(2)

                _uiStateClienteSapResponse.value = clientes
            } catch (e: Exception) {
                Log.i("ERROR CLIENTE", "${e.message}")
            }
        }
    }
}

data class UiStateOptionItem(
    val id: Int,
    val label: String
)

data class UiStateClienteItem(
    val cardCode: String,
    val cardName: String
)