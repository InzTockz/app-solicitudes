package com.battilana.app_solicitudes.ui.screens.pedido

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battilana.app_solicitudes.data.local.UserPreferences
import com.battilana.app_solicitudes.data.model.ArticulosResponse
import com.battilana.app_solicitudes.data.model.ClientesSapResponse
import com.battilana.app_solicitudes.data.model.DraftDocumentLineRequest
import com.battilana.app_solicitudes.data.model.DraftRequest
import com.battilana.app_solicitudes.data.model.ErrorResponse
import com.battilana.app_solicitudes.data.model.StockAlmacenResponse
import com.battilana.app_solicitudes.data.model.UsuarioSapResponse
import com.battilana.app_solicitudes.domain.usecase.SapUseCase
import com.battilana.app_solicitudes.domain.usecase.UsuarioSapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@HiltViewModel
class PedidoViewModel @Inject constructor(
    private val usuarioSapUseCase: UsuarioSapUseCase,
    private val sapUseCase: SapUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiStateUsuarioSapResponse = MutableStateFlow<List<UsuarioSapResponse>>(emptyList())
    val uiStateUsuarioResponse: StateFlow<List<UsuarioSapResponse>> = _uiStateUsuarioSapResponse

    private val _uiStateClienteSapResponse =
        MutableStateFlow<List<ClientesSapResponse>>(emptyList())
    val uiStateClienteSapResponse: StateFlow<List<ClientesSapResponse>> = _uiStateClienteSapResponse

    private val _uiStateArticuloResponse = MutableStateFlow<List<ArticulosResponse>>(emptyList())
    val uiStateArticuloResponse: StateFlow<List<ArticulosResponse>> = _uiStateArticuloResponse

    private val _uiStateStockResponse = MutableStateFlow<StockAlmacenResponse?>(null)
    val uiStateStockResponse: StateFlow<StockAlmacenResponse?> = _uiStateStockResponse

    private val _uiStatePedido = MutableStateFlow<List<UiStatePedido>>(emptyList())
    val uiStatePedido: StateFlow<List<UiStatePedido>> = _uiStatePedido

    //CONTROLAR EL ERROR
    private val _uiStateError = MutableStateFlow<String?>(null)
    val uiStateError: StateFlow<String?> = _uiStateError

    //CONTROLAR EL SUCCESS
    private val _uiStateDraftSuccess = MutableStateFlow<Boolean?>(null)
    val uiStateDraftSuccess: StateFlow<Boolean?> = _uiStateDraftSuccess

    private var searchJob: Job? = null

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

    fun buscarClientesSap(query: String) {

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            if(query.length<1){
                _uiStateArticuloResponse.value = emptyList()
                return@launch
            } else {
                try {
                    val session = userPreferences.userSession.first()
                    val codVendedor = session?.codigo ?: return@launch

                    val clientes = sapUseCase.listarClientesPorVendedorYCardName(
                        idVendedor = codVendedor,
                        cardName = query
                    )

                    _uiStateClienteSapResponse.value = clientes
                } catch (e: Exception){
                    Log.i("ERROR_CLIENTE", "${e.message}")
                }
            }
        }
    }

    fun buscarAreticulos(query: String) {

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            if (query.length < 1) {
                _uiStateArticuloResponse.value = emptyList()
                return@launch
            } else {
                try {
                    val usuario = userPreferences.userSession.first()
                    val idAlmacen = usuario?.almacen ?: "04"

                    val articulos = sapUseCase.listarArticulosPorAlmacen(
                        idAlmacen = idAlmacen,
                        nombre = query
                    )
                    _uiStateArticuloResponse.value = articulos
                } catch (e: Exception) {
                    Log.i("ERROR_BUSQUEDA", "No se pudo cargar la informacion: ${e.message}")
                }
            }
        }
    }

    fun cargarStockAlmacen(itemCode: String) {
        viewModelScope.launch {
            try {
                val usuario = userPreferences.userSession.first()
                val idAlmacen = usuario?.almacen ?: return@launch

                val stock = sapUseCase.stockPorArticuloYAlmacen(
                    itemCode,
                    if (idAlmacen.isEmpty()) "04" else idAlmacen
                )
                _uiStateStockResponse.value = stock
            } catch (e: Exception) {
                Log.i("ERROR_STOCK_ALMACEN", "${e.message}")
            }
        }
    }

    fun agregarArticulo(itemCode: String, cantidad: Double, itemName: String, whsCode: String) {
        val producto = UiStatePedido(
            itemCode = itemCode,
            cantidad = cantidad,
            itemName = itemName,
            whsCode = whsCode
        )
        _uiStatePedido.value = _uiStatePedido.value + producto
    }

    fun eliminarArticulo(index: Int) {
        val listaActual = _uiStatePedido.value.toMutableList()
        if (index in listaActual.indices) {
            listaActual.removeAt(index)
            _uiStatePedido.value = listaActual
        }
    }

    fun limpiarListaDeArticulos() {
        _uiStatePedido.value = emptyList()
    }

    fun limpiarStock(){
        _uiStateStockResponse.value = null
    }

    fun agregarDraft(clienteId: String, idUsuarioSap: Int, comentario: String) {
        viewModelScope.launch {
            try {
                val usuario = userPreferences.userSession.first()
                val idVendedor = usuario?.codigo.toString()
                //val idAlmacen = usuario?.almacen

                val lineas = _uiStatePedido.value.map {
                    DraftDocumentLineRequest(
                        ItemCode = it.itemCode,
                        Quantity = it.cantidad.toString(),
                        TaxCode = it.impuesto,
                        WarehouseCode = it.whsCode
                    )
                }

                val draftRequest = DraftRequest(
                    CardCode = clienteId,
                    DocObjectCode = "17",
                    SalesPersonCode = idVendedor,
                    DocumentLines = lineas,
                    Comments = comentario
                )

                val response = sapUseCase.agregarDraft(draftRequest, idUsuarioSap)

                if(response.isSuccessful){
                    val draft = response.body()
                    Log.i("DRAFT_SUCCESS", "Pedido registrado correctamente: $draft")

                    _uiStateError.value = null
                    _uiStateDraftSuccess.value = true

                    _uiStatePedido.value = emptyList()
                    _uiStateStockResponse.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val json = Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }

                    val errorDto = try {
                        errorBody?.let { json.decodeFromString<ErrorResponse>(it) }
                    } catch (e: Exception){
                        null
                    }

                    var message = errorDto?.message ?: "Error al registrar el pedido"

                    Log.i("ERROR_DRAFT", "Code: ${errorDto?.code}, Message: $message")

                    _uiStateError.value = message
                    _uiStateDraftSuccess.value = false
                }
            } catch (e: Exception) {
                Log.i("ERROR_DRAFT", "Error de conexión: ${e.message}")
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

data class UiStateArticuloItem(
    val itemCode: String,
    val itemName: String
)

data class UiStatePedido(
    val itemCode: String,
    val itemName: String,
    val cantidad: Double,
    val whsCode: String,
    val impuesto: String = "IGV"
)