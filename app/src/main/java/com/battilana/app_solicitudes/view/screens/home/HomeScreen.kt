@file:OptIn(ExperimentalMaterial3Api::class)

package com.battilana.app_solicitudes.view.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.battilana.app_solicitudes.R
import com.battilana.app_solicitudes.view.components.BattiButtonItem
import com.battilana.app_solicitudes.view.components.BattiElevatedCardNotification
import com.battilana.app_solicitudes.view.components.BattiElevatedCardSection
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
            BattiElevatedCardSection(
                text = "Acciones rapidas",
                listButton = listOf(
                    BattiButtonItem(
                        actionOnClick = {},
                        icons = R.drawable.ic_pckg_add,
                        texto = "Crear nuevo pedido preliminar"
                    ),
                    BattiButtonItem(
                        actionOnClick = {},
                        icons = R.drawable.ic_form,
                        texto = "Ver todos los pedidos"
                    )
                )
            )
        }
    }
}