@file:OptIn(ExperimentalMaterial3Api::class)

package com.battilana.app_solicitudes.view.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.battilana.app_solicitudes.R
import com.battilana.app_solicitudes.view.components.BattiElevatedCardNotification
import com.battilana.app_solicitudes.view.components.BattiTopAppBarHome

@Composable
fun HomeScreen() {

    Scaffold(
        topBar = {
            BattiTopAppBarHome(text = "Dashboard")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BattiElevatedCardNotification(
                    modifier = Modifier.weight(1f),
                    textFirst = "Total solicitudes",
                    textSecond = "24"
                )
                Spacer(Modifier.width(20.dp))
                BattiElevatedCardNotification(
                    modifier = Modifier.weight(1f),
                    textFirst = "Pendientes",
                    textSecond = "5"
                )
            }
            Spacer(Modifier.height(20.dp))
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.6f),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(text = "Acciones rapidas")
                    Spacer(Modifier.height(20.dp))
                    Button(
                        shape = RoundedCornerShape(8.dp),
                        onClick = {},
                        //modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                painter = painterResource(id = R.drawable.ic_pckg_add),
                                contentDescription = null
                            )
                            Text(text = "Crear nuevo pedido preliminar")
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Black,
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                painter = painterResource(id = R.drawable.ic_form),
                                contentDescription = null
                            )
                            Text(text = "Ver todos los pedidos")
                        }
                    }
                }

            }
//            Spacer(Modifier.height(20.dp))
//            ElevatedCard(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(2f),
//                elevation = CardDefaults.elevatedCardElevation(5.dp)
//            ) {
//                Column(
//                    modifier = Modifier.padding(15.dp)
//                ) {
//                    Text(text = "Pedidos recientes")
//                    Spacer(Modifier.height(20.dp))
//                    Row {
//                        Row {
//                            Text(text = "PO-001")
//                            Spacer(Modifier.width(5.dp))
//                            Text(text = "Pendiente")
//                        }
//                    }
//                }
//            }
        }
    }
}