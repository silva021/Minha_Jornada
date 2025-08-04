package com.silva021.minhajornada.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.accentColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        visualTransformation = visualTransformation,
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        placeholder = {
            Text(
                text = placeholder,
                color = textSecondary
            )
        },
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = accentColor,
            unfocusedContainerColor = accentColor,
            disabledContainerColor = accentColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Palette.primaryColor,
            focusedTextColor = textPrimary,
            unfocusedTextColor = textPrimary,
        ),
        readOnly = readOnly,
        shape = RoundedCornerShape(16.dp),
        trailingIcon = trailingIcon,
    )
}