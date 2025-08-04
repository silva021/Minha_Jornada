package com.silva021.minhajornada.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.textPrimary

@Composable
fun Header(
    title: String? = null,
    onBackPressed: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        onBackPressed?.let {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterStart)
                    .padding(start = 6.dp)
                    .clip(CircleShape)
                    .background(cardBackground)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White
                )
            }
        }

        title?.let {
            Text(
                text = title,
                color = textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Column(Modifier.background(backgroundColor)) {
        Header(
            title = "Título do Header",
            onBackPressed = {}
        )
    }
}