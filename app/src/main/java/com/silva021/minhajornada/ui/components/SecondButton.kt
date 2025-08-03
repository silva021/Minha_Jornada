package com.silva021.minhajornada.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.ui.theme.Palette.accentColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary

@Composable
fun SecondButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(50),
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = accentColor,
            contentColor = textPrimary
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

