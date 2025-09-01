@file:OptIn(ExperimentalMaterial3Api::class)

package com.battilana.app_solicitudes.view.screens.pedido

import android.view.RoundedCorner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun PedidoScreen() {

    val data = (1..30).toList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nuevo pedido Preliminar") },
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
//                    }
//                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                //.verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = { Text(text = "Asignado a") },
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = { Text(text = "Cliente") },
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = { Text(text = "Vendedor") },
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = { Text(text = "Producto") },
                shape = RoundedCornerShape(12.dp)
            )
            Row {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Cantidad") },
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(Modifier.width(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Stock") },
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(Modifier.width(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Almacen") },
                    shape = RoundedCornerShape(12.dp)
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text(text = "Agregar Producto")
            }

            /*
            SECCION DEL GRILL PARA LA TABLA
            */

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),

            ) {
                items(data.size){ item ->
                    Card {
                        Text(text = "Pruebita :" + data[item])
                    }
                }
            }

            Spacer(Modifier.height(50.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text(text = "Registrar Pedido")
            }
            Row {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {}
                ) {
                    Text(text = "Limpiar tabla")
                }
                Spacer(Modifier.width(10.dp))
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {}
                ) {
                    Text(text = "Cancelar pedido")
                }
            }

        }
    }
}