package com.battilana.app_solicitudes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BattiSelect(
    label: String,
    options: List<T>,
    selectedOption: T?,
    labelMapper: (T) -> String,
    onSelected: (T) -> Unit,
    singleLine: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredOptions = remember(searchQuery, options) {
        if (searchQuery.isEmpty()) options
        else options.filter {
            labelMapper(it).contains(searchQuery, ignoreCase = true)
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = searchQuery.ifEmpty { selectedOption?.let(labelMapper) ?: "" },
            onValueChange = {
                searchQuery = it
                expanded = true
            },
            label = { Text(text = label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            singleLine = singleLine
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.heightIn(max = 300.dp)
        ) {

            if (filteredOptions.isEmpty()) {
                DropdownMenuItem(
                    text = { Text(text = "Sin resultados") },
                    onClick = {}
                )
            } else {
                filteredOptions.forEach { option ->
                    DropdownMenuItem(
                        text = {Text(text = labelMapper(option))},
                        onClick = {
                            onSelected(option)
                            searchQuery = labelMapper(option)
                            expanded = false
                        }
                    )
                }
            }

//            filteredOptions.forEach { option ->
//                DropdownMenuItem(
//                    text = {Text(text = labelMapper(option))},
//                    onClick = {
//                        onSelected(option)
//                        searchQuery = labelMapper(option)
//                        expanded = false
//                    }
//                )
//            }
//            options.forEach { option ->
//                DropdownMenuItem(
//                    text = { Text(text = labelMapper(option)) },
//                    onClick = {
//                        onSelected(option)
//                        expanded = false
//                    }
//                )
//            }
        }
    }
}