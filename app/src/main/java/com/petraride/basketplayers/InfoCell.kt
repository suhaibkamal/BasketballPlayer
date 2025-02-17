package com.petraride.basketplayers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun infoCell(title: String, value: String,color: Color) {

    OutlinedTextField(
        value = value,
        onValueChange = {},
        readOnly = true,
        label = { Text(text = title, style = MaterialTheme.typography.bodyMedium) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = LocalTextStyle.current.copy(color = color),
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = color,
            disabledBorderColor = color,
            disabledLabelColor = color
        ),
        enabled = false // Ensures it's not editable
    )
}
