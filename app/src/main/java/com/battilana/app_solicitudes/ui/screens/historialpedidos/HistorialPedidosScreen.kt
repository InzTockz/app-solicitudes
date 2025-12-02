package com.battilana.app_solicitudes.ui.screens.historialpedidos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.battilana.app_solicitudes.R
import com.battilana.app_solicitudes.ui.components.BattiButtonSimple
import com.battilana.app_solicitudes.ui.components.BattiIcons
import com.battilana.app_solicitudes.ui.components.BattiOutLinedTextFieldIconFind
import com.battilana.app_solicitudes.ui.theme.customBackGroundColor

@Composable
fun HistorialPedidosScreen(
    historialPedidosViewModel: HistorialPedidosViewModel = hiltViewModel()
) {

    val pedidos by historialPedidosViewModel.uiStateDraftSap.collectAsState()

    LaunchedEffect(Unit) {
        historialPedidosViewModel.cargarDrafts()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .background(customBackGroundColor)
                    .padding(top = 20.dp)
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Spacer(
//                    Modifier
//                        .height(30.dp)
//                )
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
        }
        items(pedidos) { p ->
            Spacer(Modifier.height(20.dp))
            ElevatedCard(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .wrapContentHeight(),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 20.dp
                ),
            ) {
                Row(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Row {
                            Text(
                                modifier = Modifier
                                    .background(
                                        color = if(p.wddStatus.equals("W")) Color.Yellow else if (p.wddStatus.equals("A")) Color.Green else Color.LightGray
                                        , shape = RoundedCornerShape(10.dp))
                                    .padding(6.dp),
                                text = if(p.wddStatus.equals("W")) "Pendiente" else "Generado",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )
                            Spacer(Modifier.weight(3f))
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = "${p.docEntry}",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )
                        }
                        BasicTextField(
                            value = p.cardName,
                            modifier = Modifier.padding(top = 20.dp).padding(bottom = 20.dp),
                            onValueChange = {},
                            singleLine = true,
                            readOnly = true,
                            textStyle = TextStyle(
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(text = "Creado: ${p.createDate}", fontSize = 20.sp)
                    }
                    Spacer(Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text(modifier = Modifier, text = "Monto total:")
                        Text(text = "S/.${p.docTotal}")
                        Spacer(Modifier.height(40.dp))
                        BattiButtonSimple(
                            Modifier.fillMaxWidth(),
                            onClick = {},
                            text = "Ver",
                            enabled = false
                        )
                    }
                }
            }
        }
        item {
            Spacer(Modifier.height(10.dp))
        }
    }
}