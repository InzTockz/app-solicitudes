package com.battilana.app_solicitudes.view.screens.HistorialPedidosScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.battilana.app_solicitudes.R

@Composable
fun HistorialPedidosScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {
        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = "",
                onValueChange = {},
                label = { Text(text = "Buscar por codigo") },
                shape = RoundedCornerShape(20),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            )
            Spacer(Modifier.width(10.dp))
            Icon(
                modifier = Modifier
//                    .weight(0.15f)
                    .clickable{}
                    .padding(15.dp),
                painter = painterResource(R.drawable.ic_filter), contentDescription = null
            )
        }
        Spacer(Modifier.height(20.dp))
        ElevatedCard(
            modifier = Modifier,
            elevation = CardDefaults.elevatedCardElevation()
        ) {
            Row(
                modifier = Modifier.padding(12.dp)
            ) {
                Column {
                    Row {
                        Text(modifier = Modifier, text = "PO-001")
                        Spacer(Modifier.width(10.dp))
                        Text(modifier = Modifier, text = "Pendiente")
                    }
                    Text(text = "Empresa ABC S.A.")
                    Spacer(Modifier.height(30.dp))
                    Text(text = "Creado: 2024-01-15")
                }
                Spacer(Modifier.weight(1f))
                Column {
                    Text(modifier = Modifier, text = "$15,000.00")
                    Text(text = "3 productos")
                    Spacer(Modifier.height(15.dp))
                    Button(
                        onClick = {}
                    ) {
                        Text(text = "Descargar")
                    }
                }

            }
        }
    }
}