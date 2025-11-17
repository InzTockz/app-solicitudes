package com.battilana.app_solicitudes.ui.screens.historialpedidos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.battilana.app_solicitudes.R
import com.battilana.app_solicitudes.ui.components.BattiButtonSimple
import com.battilana.app_solicitudes.ui.components.BattiIcons
import com.battilana.app_solicitudes.ui.components.BattiOutLinedTextFieldIconFind
import com.battilana.app_solicitudes.ui.components.BattiTextField
import com.battilana.app_solicitudes.ui.theme.customBackGroundColor
import dagger.hilt.android.lifecycle.HiltViewModel

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
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp),
                elevation = CardDefaults.elevatedCardElevation(),
            ) {
                Row(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Row {
                            Text(modifier = Modifier, text = "${p.docEntry}")
                            Spacer(Modifier.width(10.dp))
                            Text(
                                modifier = Modifier,
                                text = if(p.wddStatus.equals("W")) "Pendiente" else "Generado"
                            )
                        }
                        BasicTextField(
                            value = p.cardName,
                            onValueChange = {},
                            singleLine = true,
                            readOnly = true,
                            modifier = Modifier.padding().fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent,
                                errorBorderColor = Color.Transparent,

                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                disabledTextColor = Color.Black,
                                errorTextColor = Color.Black,

                                focusedLabelColor = Color.Transparent,
                                unfocusedLabelColor = Color.Transparent,
                                disabledLabelColor = Color.Transparent,
                                errorLabelColor = Color.Transparent,

                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                errorContainerColor = Color.Transparent,

                                cursorColor = Color.Transparent
                            ),
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(text = "Creado: ${p.createDate}")
                    }
                    Spacer(Modifier.width(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Text(modifier = Modifier, text = "S/.${p.docTotal}")
                        Text(text = "3 productos")
                        Spacer(Modifier.height(15.dp))
                        Spacer(Modifier.weight(1f))
                        BattiButtonSimple(
                            Modifier.fillMaxWidth(),
                            onClick = {},
                            text = "Ver"
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