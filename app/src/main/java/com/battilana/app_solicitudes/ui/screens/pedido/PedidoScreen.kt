@file:OptIn(ExperimentalMaterial3Api::class)

package com.battilana.app_solicitudes.ui.screens.pedido

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.battilana.app_solicitudes.ui.components.BattiTextField
import com.battilana.app_solicitudes.ui.components.BattiButton
import com.battilana.app_solicitudes.ui.components.BattiOutLinedButton
import com.battilana.app_solicitudes.ui.components.BattiSelect

@Composable
fun PedidoScreen(
    pedidoViewModel: PedidoViewModel = hiltViewModel()
) {

    val usuarios by pedidoViewModel.uiStateUsuarioResponse.collectAsState()
    var selectedUsuarioSap by remember { mutableStateOf<UiStateOptionItem?>(null) }

    val clientes by pedidoViewModel.uiStateClienteSapResponse.collectAsState()
    var selectedClienteSap by remember { mutableStateOf<UiStateClienteItem?>(null) }

    val productos by remember { mutableStateOf("") }
    val titulos = remember { mutableListOf<String>(
        "Producto", "Cantidad", "Almacen", "Accion"
    )}

    val elementos = listOf(
        UiStateOptionItem(1, "Primer item"),
        UiStateOptionItem(2, "Segundo item"),
        UiStateOptionItem(3, "Tercero item"),
        UiStateOptionItem(4, "Cuarto item"),
        UiStateOptionItem(5, "Quinto item")
    )
    var selectedOption by remember { mutableStateOf<UiStateOptionItem?>(null) }

    LaunchedEffect(Unit) {
        pedidoViewModel.cargarUsuariosSap()
        pedidoViewModel.cargarClientesSap()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nuevo pedido Preliminar") },
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
                labelMapper = { it.label},
                onSelected= {
                    selectedUsuarioSap = it
                }
            )
            BattiSelect(
                label = "Seleccione un cliente",
                options = clientes.map {
                    UiStateClienteItem(it.cardCode, it.cardName)
                },
                selectedOption = selectedClienteSap,
                labelMapper = {it.cardName},
                onSelected = {
                    selectedClienteSap = it
                }
            )
            Spacer(Modifier.height(8.dp))
//            BattiSelect(
//                label = "Seleccione un producto",
//                options = elementos.map {
//
//                },
//                selectedOptions = selectedOption,
//                onSelected = { selectedOption = it}
//            )
            Row {
                BattiTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = "",
                    onValueChange = {},
                    label = "Cantidad",
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(Modifier.width(10.dp))
                BattiTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = "",
                    onValueChange = {},
                    label = "Stock",
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(Modifier.width(10.dp))
                BattiTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = "",
                    onValueChange = {},
                    label = "Almacen",
                    shape = RoundedCornerShape(12.dp)
                )
            }
            BattiButton(
                onClick = {},
                text = "Agregar Producto"
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                )
                {
                    Row(
                        Modifier.fillMaxWidth(),
//                        horizontalArrangement = Alignment.CenterHorizontally
                    ) {
                        titulos.forEach { response ->
                            Text(text = response, Modifier.weight(1f))
                        }
                    }
                    HorizontalDivider(thickness = 2.dp)
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Arroz", Modifier.weight(1f))
                        Text("15", Modifier.weight(1f))
                        Text("15", Modifier.weight(1f))
                        TextButton(modifier = Modifier
                            .weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                            onClick = {}) { Text(text = "Quitar")}
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Avivat caninos x bolsa", Modifier.weight(1f))
                        Text("20", Modifier.weight(1f))
                        Text("15", Modifier.weight(1f))
                        TextButton(modifier = Modifier
                            .weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                            onClick = {}) { Text(text = "Quitar")}
                    }
                }
            }

            Spacer(Modifier.height(50.dp))
            BattiButton(
                onClick = {},
                text = "Registrar Pedido"
            )
            Row {
                BattiOutLinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {},
                    text = "Limpiar tabla"
                )
            }

        }
    }
}