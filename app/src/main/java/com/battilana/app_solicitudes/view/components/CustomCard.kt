package com.battilana.app_solicitudes.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BattiElevatedCardNotification(
    modifier: Modifier = Modifier,
    elevation: CardElevation = CardDefaults.elevatedCardElevation(5.dp),
    textFirst: String,
    textSecond: String
) {
    ElevatedCard(
        modifier = modifier
            .aspectRatio(1.4f),
        elevation = elevation
    ) {
        Column (
            modifier = Modifier.padding(15.dp)
        ) {
            Text(text = textFirst, style = MaterialTheme.typography.labelMedium)
            Text(text = textSecond)
        }
    }
}