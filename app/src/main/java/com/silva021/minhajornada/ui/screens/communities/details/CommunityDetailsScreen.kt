package com.silva021.minhajornada.ui.screens.communities.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.primaryColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@Composable
fun CommunityDetailsScreen(
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        Header (
            "Comunidade",
            onBackPressed = onBackPressed,
        )
        CommunityProfileSection()
        AboutSection()
    }
}

@Composable
private fun CommunityProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuDKDEhBCipEPfz0qcXTQPr6WRA_CYY9b1T-IpAc5UP7VuWlKzOrUe60aHLSNGpmtE8nDPeyN5B113RtWEFTmOxWFoa0NSMAXGh-EfjvX1qKIuuv7XinJ33a-DA45lb2fSM6cU73r5m144bqXCcXlDv0I4PD-9USokm6toxz-CnagrWdJ49NplSX26M9xrRTSFZso6nVepnEw_mDIwZca3yAzzd__c902iIJV-Z4nHudIGFa_fiogWkK2twq1EaxuCy_QcNPk8tB4fg",
            contentDescription = "Foto da comunidade",
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Buscadores de Desafios",
                color = textPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Uma comunidade para quem ama desafiar a si mesmo e aos outros.",
                color = textSecondary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

        // Botão de participar
        Button(
            onClick = { /* Participar da comunidade */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                contentColor = backgroundColor
            ),
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(48.dp)
        ) {
            Text(
                text = "Participar da Comunidade",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun AboutSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Sobre",
            color = textPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Esta comunidade é para pessoas que amam desafiar a si mesmas e aos outros. Compartilhe seus desafios, obtenha apoio e encontre inspiração.",
            color = textSecondary,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }
}



@Preview
@Composable
fun CommunityDetailsScreenPreview() {
    CommunityDetailsScreen(
        onBackPressed = {}
    )
}