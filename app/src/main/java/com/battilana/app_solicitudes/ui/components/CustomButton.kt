package com.battilana.app_solicitudes.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun BattiButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit,
    shape: Shape = RoundedCornerShape(25),
    text: String
){
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = shape
    ) {
        Text(text = text)
    }
}

@Composable
fun BattiButtonSimple(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    shape: Shape = RoundedCornerShape(25),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    enabled: Boolean = true
){
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
        enabled = enabled
    ) {
        Text(text = text)
    }
}

@Composable
fun BattiOutLinedButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit,
    shape: Shape = RoundedCornerShape(25),
    text: String,

){
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = shape
    ) {
        Text(text = text)
    }
}