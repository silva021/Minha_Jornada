package com.silva021.minhajornada.ui.screens.communities.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import com.silva021.minhajornada.domain.model.Community
import com.silva021.minhajornada.domain.model.toDomain
import com.silva021.minhajornada.ui.DatabaseFake
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@Composable
fun CommunityDetailsContent(
    community: Community,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        Header(
            "Comunidade",
            onBackPressed = onBackPressed,
        )
        CommunityProfileSection(community)
        AboutSection(community)
    }
}

@Composable
private fun CommunityProfileSection(community: Community) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
//            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuDKDEhBCipEPfz0qcXTQPr6WRA_CYY9b1T-IpAc5UP7VuWlKzOrUe60aHLSNGpmtE8nDPeyN5B113RtWEFTmOxWFoa0NSMAXGh-EfjvX1qKIuuv7XinJ33a-DA45lb2fSM6cU73r5m144bqXCcXlDv0I4PD-9USokm6toxz-CnagrWdJ49NplSX26M9xrRTSFZso6nVepnEw_mDIwZca3yAzzd__c902iIJV-Z4nHudIGFa_fiogWkK2twq1EaxuCy_QcNPk8tB4fg",
            model = community.imageUrl,
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
//                text = "Buscadores de Desafios",
                text = community.name,
                color = textPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = community.description,
//                text = "Uma comunidade para quem ama desafiar a si mesmo e aos outros.",
                color = textSecondary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

        PrimaryButton(
            onClick = { /* Participar da comunidade */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            text = "Participar da Comunidade",
        )
    }
}

@Composable
private fun AboutSection(community: Community) {
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
            text = community.about,
//            text = "Esta comunidade é para pessoas que amam desafiar a si mesmas e aos outros. Compartilhe seus desafios, obtenha apoio e encontre inspiração.",
            color = textSecondary,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }
}

@Preview
@Composable
fun CommunityDetailsContentPreview() {
    CommunityDetailsContent(
        community = DatabaseFake.communities.my.first().toDomain(),
        onBackPressed = {}
    )
}