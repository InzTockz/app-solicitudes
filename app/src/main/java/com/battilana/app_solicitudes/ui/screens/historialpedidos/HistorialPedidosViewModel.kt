package com.battilana.app_solicitudes.ui.screens.historialpedidos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battilana.app_solicitudes.data.local.UserPreferences
import com.battilana.app_solicitudes.data.model.DraftSapResponse
import com.battilana.app_solicitudes.domain.usecase.SapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class HistorialPedidosViewModel @Inject constructor(
    private val sapUseCase: SapUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiStateDraftSap = MutableStateFlow<List<DraftSapResponse>>(emptyList())
    val uiStateDraftSap: StateFlow<List<DraftSapResponse>> = _uiStateDraftSap

    fun cargarDrafts() {
        viewModelScope.launch {
            try {
                val usuario = userPreferences.userSession.first()
                val idVendedor = usuario?.codigo ?: return@launch

                _uiStateDraftSap.value =
                    sapUseCase.listarDraftsPorVendedorYFecha(idVendedor, "2025-11-01", "2025-12-31")

            } catch (e: Exception) {
                Log.i("ERROR_DRAFTS", "Error al cargar los drafts")
            }
        }
    }
}

data class UiStateDraftSap(
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
    val wddStatus: String
)