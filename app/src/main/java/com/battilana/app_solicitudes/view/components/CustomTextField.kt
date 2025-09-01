package com.battilana.app_solicitudes.view.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.battilana.app_solicitudes.R

@Composable
fun BattiTextFieldPassword(
    modifier: Modifier = Modifier,
    value: String,
    onTextFieldValue: (String) -> Unit,
    shape: Shape = RoundedCornerShape(30),
    label: String,
    singleLine: Boolean = true,
    keyOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    visualTransformation: Boolean,
    iconButtonAction: () -> Unit,
    iconImage: Boolean,

    ) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onTextFieldValue,
        shape = shape,
        label = { Text(text = label) },
        singleLine = singleLine,
        keyboardOptions = keyOptions,
        visualTransformation = if (visualTransformation) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = iconButtonAction) {
                Icon(
                    imageVector = ImageVector.vectorResource(if (iconImage) R.drawable.ic_show_eye else R.drawable.ic_hide_eye),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun BattiTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange:(String) -> Unit,
    shape: Shape = RoundedCornerShape(30),
    singleLine: Boolean = true,
    label: String
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        shape = shape,
        singleLine = singleLine,
        label = { Text(text = label) }
    )
}