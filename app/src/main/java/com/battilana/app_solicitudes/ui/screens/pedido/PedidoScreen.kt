@file:OptIn(ExperimentalMaterial3Api::class)

package com.battilana.app_solicitudes.ui.screens.pedido

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.battilana.app_solicitudes.R
import com.battilana.app_solicitudes.ui.components.AutoCompleteSelectM3
import com.battilana.app_solicitudes.ui.components.BattiTextField
import com.battilana.app_solicitudes.ui.components.BattiButton
import com.battilana.app_solicitudes.ui.components.BattiOutLinedButton
import com.battilana.app_solicitudes.ui.components.BattiSelect
import com.battilana.app_solicitudes.ui.components.BattiText
import com.battilana.app_solicitudes.ui.theme.battiOrangeColor

@Composable
fun PedidoScreen(
    pedidoViewModel: PedidoViewModel = hiltViewModel()
) {

    val usuarios by pedidoViewModel.uiStateUsuarioResponse.collectAsState()
    var selectedUsuarioSap by remember { mutableStateOf<UiStateOptionItem?>(null) }

    val clientes by pedidoViewModel.uiStateClienteSapResponse.collectAsState()
    var selectedClienteSap by remember { mutableStateOf<UiStateClienteItem?>(null) }

    val articulos by pedidoViewModel.uiStateArticuloResponse.collectAsState()
    var selectedArticulo by remember { mutableStateOf<UiStateArticuloItem?>(null) }

    val stockResponse by pedidoViewModel.uiStateStockResponse.collectAsState()

    var comentario by remember { mutableStateOf("") }

    val articulosAgregados by pedidoViewModel.uiStatePedido.collectAsState()
    var cantidad by remember { mutableStateOf("") }

    var clienteText by remember { mutableStateOf("") }
    var articuloText by remember { mutableStateOf("") }

    val draftSuccess by pedidoViewModel.uiStateDraftSuccess.collectAsState()
    val errorMessage by pedidoViewModel.uiStateError.collectAsState()


    LaunchedEffect(Unit) {
        pedidoViewModel.cargarUsuariosSap()
    }

    LaunchedEffect(draftSuccess) {
        if(draftSuccess == true){
            comentario = ""
            cantidad = ""
            clienteText = ""
            articuloText = ""
            selectedClienteSap = null
            selectedArticulo = null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nuevo pedido") },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            BattiSelect(
                label = "Asignado a",
                options = usuarios.map {
                    UiStateOptionItem(it.idUsuarioSap.toInt(), it.nombreUsuario)
                },
                selectedOption = selectedUsuarioSap,
                labelMapper = { it.label },
                onSelected = {
                    selectedUsuarioSap = it
                }
            )
            Spacer(Modifier.height(10.dp))
            AutoCompleteSelectM3(
                label = "Clientes",
                items = clientes.map { UiStateClienteItem(it.cardCode, it.cardName) },
                itemLabel = {it.cardName},
                text = clienteText,
                onTextChange = { newText ->
                    clienteText = newText
                },
                onItemSelected = { cliente ->
                    selectedClienteSap = cliente
                    clienteText = cliente.cardName
                },
                onSearch = { query ->
                    pedidoViewModel.buscarClientesSap(query)
                }
            )
            Spacer(Modifier.height(20.dp))
            AutoCompleteSelectM3(
                label = "Articulos/Productos",
                items = articulos.map { UiStateArticuloItem(it.itemCode, it.itemName) },
                itemLabel = { it.itemName},
                text = articuloText,
                onTextChange = { newText ->
                    articuloText = newText
                },
                onItemSelected = { item ->
                    selectedArticulo = item
                    articuloText = item.itemName
                    pedidoViewModel.cargarStockAlmacen(item.itemCode)
                },
                onSearch = { query ->
                    pedidoViewModel.buscarAreticulos(query)
                }
            )
            Spacer(Modifier.height(10.dp))
            Row {
                BattiTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = cantidad,
                    onValueChange = {
                        cantidad = it
                    },
                    label = "Cantidad",
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(Modifier.width(10.dp))
                BattiTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = stockResponse?.stockTotal?.toString() ?: "",
                    onValueChange = {},
                    label = "Stock",
                    shape = RoundedCornerShape(12.dp),
                    readOnly = true
                )
                Spacer(Modifier.width(10.dp))
                BattiTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = stockResponse?.unidadMedida ?: "",
                    onValueChange = {},
                    label = "U. Medida",
                    shape = RoundedCornerShape(12.dp),
                    readOnly = true
                )
            }
            Spacer(Modifier.height(5.dp))
            BattiButton(
                onClick = {
                    selectedArticulo?.let {
                        val cantidadDouble = cantidad.toDoubleOrNull() ?: 0.0
                        val idAlmacen = stockResponse?.codigoAlmacen ?: return@BattiButton
                        if (cantidadDouble > 0) {
                            pedidoViewModel.agregarArticulo(
                                itemCode = it.itemCode,
                                itemName = it.itemName,
                                cantidad = cantidadDouble,
                                whsCode = idAlmacen
                            )
                            cantidad = "" //limpiamos el campito eae
//                            selectedArticulo = null
                            articuloText = ""
                            pedidoViewModel.limpiarStock()
                        }
                    }
                },
                text = "Agregar Producto",
            )
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                )
                {
                    Row(
                        Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primaryContainer).padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BattiText("Articulo", modifier = Modifier.weight(2f))
                        BattiText("Cantidad", modifier = Modifier.weight(2f))
                        BattiText("Accion", modifier = Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(4.dp))
                    HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

                    articulosAgregados.forEachIndexed { index, articulo ->

                        val fondo = if(index % 2 == 0) Color(0xFFF7F7F7) else Color.Transparent

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().background(fondo).padding(vertical = 8.dp),
                        ) {
                            Text(
                                articulo.itemName,
                                Modifier
                                    .weight(2f)
                                    .padding(horizontal = 4.dp),
                                textAlign = TextAlign.Center
                            )
                            Text(articulo.cantidad.toString(),
                                Modifier
                                    .weight(2f)
                                    .padding(horizontal = 4.dp),
                                textAlign = TextAlign.Center
                            )
                            Icon(
                                modifier = Modifier
                                    .size(20.dp)
                                    .weight(1f)
                                    .clickable{
                                        pedidoViewModel.eliminarArticulo(index)
                                    }
                                    .align(Alignment.CenterVertically),
                                painter = painterResource(id = R.drawable.ic_trash),
                                contentDescription = "Trash",
                                tint = Color.Red
                            )
                        }
                        HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
                    }
                    if(articulosAgregados.isEmpty()){
                        Text(
                            text = "No hay productos agregados",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            BattiTextField(
                modifier = Modifier,
                value = comentario,
                onValueChange = {
                    comentario = it
                },
                label = "Comentario",
            )
            Spacer(Modifier.height(10.dp))
            if(errorMessage != null){
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
            BattiButton(
                onClick = {



                    val clienteId = selectedClienteSap?.cardCode ?: return@BattiButton
                    val idUsuarioSap = selectedUsuarioSap?.id ?: return@BattiButton

                    pedidoViewModel.agregarDraft(
                        clienteId = clienteId,
                        idUsuarioSap = idUsuarioSap,
                        comentario = comentario.ifEmpty { "" }
                    )

//                    if(errorMessage!=null && errorMessage.equals("")){
//                        cantidad = ""
//                        comentario = ""
//
//                        clienteText = ""
//                        articuloText = ""
//
//                        pedidoViewModel.limpiarListaDeArticulos()
//                        pedidoViewModel.limpiarStock()
//                    }

                },
                text = "Registrar Pedido"
            )
            Row {
                BattiOutLinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        pedidoViewModel.limpiarListaDeArticulos()
                    },
                    text = "Limpiar tabla"
                )
            }

        }
    }
}