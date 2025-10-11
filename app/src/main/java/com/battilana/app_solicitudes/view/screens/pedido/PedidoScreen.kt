@file:OptIn(ExperimentalMaterial3Api::class)

package com.battilana.app_solicitudes.view.screens.pedido

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.battilana.app_solicitudes.view.components.BattiTextField
import com.battilana.app_solicitudes.view.components.BattiButton
import com.battilana.app_solicitudes.view.components.BattiOutLinedButton

@Composable
fun PedidoScreen() {

    val data = (1..60).toList()
    val productos by remember { mutableStateOf("") }
    val titulos = remember { mutableListOf<String>(
        "Producto", "Cantidad", "Almacen", "Accion"
    ) }
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
            BattiTextField(
                value = "",
                onValueChange = {},
                label = "Asignado a",
                shape = RoundedCornerShape(12.dp)
            )
            BattiTextField(
                value = "",
                onValueChange = {},
                label = "Cliente",
                shape = RoundedCornerShape(12.dp)
            )
            BattiTextField(
                value = "",
                onValueChange = {},
                label = "Vendedor",
                shape = RoundedCornerShape(12.dp)
            )
            BattiTextField(
                value = "",
                onValueChange = {},
                label = "Producto",
                shape = RoundedCornerShape(12.dp)
            )
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
                Spacer(Modifier.width(10.dp))
                BattiOutLinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {},
                    text = "Cancelar pedido"
                )
            }

        }
    }
}