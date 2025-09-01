@file:OptIn(ExperimentalMaterial3Api::class)

package com.battilana.app_solicitudes.view.screens.home

import android.R
import android.graphics.drawable.Icon
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Dashboard")
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Notifications, contentDescription = null
                    )
                    Spacer(Modifier.width(20.dp))
                    Icon(
                        modifier = Modifier.padding(end = 20.dp),
                        imageVector = Icons.Default.Person, contentDescription = null
                    )
                }
            )
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
                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1.4f),
                    elevation = CardDefaults.elevatedCardElevation(5.dp)
                ) {
                    Column (
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Text(text = "Todas las solicitudes", style = MaterialTheme.typography.labelMedium)
                        Text(text = "24")
                    }
                }
                Spacer(Modifier.width(20.dp))
                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1.4f),
                    elevation = CardDefaults.elevatedCardElevation(5.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Text(text = "Pendientes")
                        Text(text = "24")
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Card(

            ) {
                Text(text = "Acciones rapidas")
            }
        }
    }
}