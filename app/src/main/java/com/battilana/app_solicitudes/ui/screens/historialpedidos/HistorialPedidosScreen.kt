package com.battilana.app_solicitudes.ui.screens.historialpedidos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.battilana.app_solicitudes.R
import com.battilana.app_solicitudes.ui.components.BattiButtonSimple
import com.battilana.app_solicitudes.ui.components.BattiIcons
import com.battilana.app_solicitudes.ui.components.BattiOutLinedTextFieldIconFind

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
            BattiOutLinedTextFieldIconFind(
                modifier = Modifier
                    .weight(1f),
                value = "",
                onTextValueChange = {},
                label = "Buscar por codigo",
                shape = RoundedCornerShape(20),
                icon = Icons.Default.Search
            )
            Spacer(Modifier.width(10.dp))
            BattiIcons(
                modifier = Modifier,
                icon = R.drawable.ic_filter
            )
        }
        Spacer(Modifier.height(20.dp))
        ElevatedCard(
            modifier = Modifier
                .wrapContentHeight(),
            elevation = CardDefaults.elevatedCardElevation(),
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
                    BattiButtonSimple(
                        onClick = {},
                        text = "Descargar"
                    )
                }
            }
        }
    }
}