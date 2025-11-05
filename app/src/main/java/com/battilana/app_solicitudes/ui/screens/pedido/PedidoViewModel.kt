package com.battilana.app_solicitudes.ui.screens.pedido

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battilana.app_solicitudes.data.local.UserPreferences
import com.battilana.app_solicitudes.data.model.ArticulosResponse
import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.data.model.StockAlmacenResponse
import com.battilana.app_solicitudes.data.model.UsuarioSapResponse
import com.battilana.app_solicitudes.domain.usecase.SapUseCase
import com.battilana.app_solicitudes.domain.usecase.UsuarioSapUseCase
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

    private val _uiStateArticuloResponse = MutableStateFlow<List<ArticulosResponse>>(emptyList())
    val uiStateArticuloResponse: StateFlow<List<ArticulosResponse>> = _uiStateArticuloResponse

    private val _uiStateStockResponse = MutableStateFlow<StockAlmacenResponse?>(null)
    val uiStateStockResponse: StateFlow<StockAlmacenResponse?> = _uiStateStockResponse

    private val _uiStatePedido = MutableStateFlow< List<UiStatePedido>>(emptyList())
    val uiStatePedido: StateFlow<List<UiStatePedido>> = _uiStatePedido

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
                val idUsuario = session?.codigo ?: return@launch
                Log.i("CLIENTE_ID", "$idUsuario")
                val clientes = sapUseCase.listarClientesPorVendedor(if (idUsuario != null) idUsuario else 0)

                _uiStateClienteSapResponse.value = clientes
            } catch (e: Exception) {
                Log.i("ERROR CLIENTE", "${e.message}")
            }
        }
    }

    fun cargarArticulosSap(){
        viewModelScope.launch {
            try {
                val usuario = userPreferences.userSession.first()
                val idAlmacen = usuario?.almacen ?: return@launch

                val articulos = sapUseCase.listarArticulosPorAlmacen(if(idAlmacen.isEmpty()) "04" else idAlmacen)
                _uiStateArticuloResponse.value = articulos
            } catch (e: Exception){
                Log.i("ERROR_ARTICULOS", "${e.message}")
            }
        }
    }

    fun cargarStockAlmacen(itemCode: String){
        viewModelScope.launch {
            try {
                val usuario = userPreferences.userSession.first()
                val idAlmacen = usuario?.almacen ?: return@launch

                val stock = sapUseCase.stockPorArticuloYAlmacen(itemCode, if(idAlmacen.isEmpty()) "04" else idAlmacen)
                _uiStateStockResponse.value = stock
            } catch (e: Exception){
                Log.i("ERROR_STOCK_ALMACEN", "${e.message}")
            }
        }
    }

    fun agregarArticulo(itemCode: String, cantidad: Double, itemName: String){
        val producto = UiStatePedido(itemCode = itemCode, cantidad = cantidad, itemName = itemName)
        _uiStatePedido.value = _uiStatePedido.value + producto
    }

    fun eliminarArticulo(itemCode: String){
        _uiStatePedido.value = _uiStatePedido.value.filterNot { it.itemCode == itemCode }
    }

    fun limpiarListaDeArticulos(){
        _uiStatePedido.value = emptyList()
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

data class UiStateArticuloItem(
    val itemCode: String,
    val itemName: String
)

data class UiStatePedido(
    val itemCode:String,
    val itemName:String,
    val cantidad: Double,
    val impuesto: String = "IGV"
)