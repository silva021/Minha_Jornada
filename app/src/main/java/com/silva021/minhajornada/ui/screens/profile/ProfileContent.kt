package com.silva021.minhajornada.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.domain.extension.getYears
import com.silva021.minhajornada.domain.model.Profile
import com.silva021.minhajornada.domain.model.SettingsItem
import com.silva021.minhajornada.domain.model.toDomain
import com.silva021.minhajornada.ui.profilesDTO
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.accentColor
import com.silva021.minhajornada.ui.theme.Palette.dividerColor
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    profile: Profile,
    onEditProfileClick: () -> Unit,
    onHelpClick: () -> Unit,
    onContactUsClick: () -> Unit,
    onRemindersClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.backgroundColor)
            .padding(horizontal = 16.dp)
    ) {
        item {
            ProfileHeader(profile)
        }

        item {
            SectionCard(
                title = "Conta",
                items = listOf(
                    SettingsItem("Editar Perfil", onClick = onEditProfileClick),
                    SettingsItem("Lembretes", onClick = onRemindersClick),
                )
            )
        }

//        item {
//            SectionCard(
//                title = "Preferências",
//                items = listOf(
//                    SettingsItem("Idioma", value = "Português"),
//                    SettingsItem("Tema", value = "Sistema")
//                )
//            )
//        }

        item {
            SectionCard(
                title = "Suporte",
                items = listOf(
                    SettingsItem("Ajuda", onClick = onHelpClick),
                    SettingsItem("Contate-nos", onClick = onContactUsClick)
                )
            )
        }
    }
}

@Composable
fun ProfileHeader(profile: Profile) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        AsyncImage(
            model = profile.profilePictureUrl,
            contentDescription = "Foto de Perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = profile.name,
            color = Palette.textPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = profile.userName,
            color = textSecondary,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Membro desde " + profile.createdAt.getYears(),
            color = textSecondary,
            fontSize = 14.sp
        )
    }
}

@Composable
fun SectionCard(title: String, items: List<SettingsItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(accentColor)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            color = Palette.textPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        items.forEachIndexed { index, item ->
            ProfileListItem(item = item)
            if (index < items.lastIndex) {
                HorizontalDivider(
                    color = dividerColor,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun ProfileListItem(item: SettingsItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { item.onClick?.invoke() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = item.text,
            color = Palette.textPrimary,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        if (item.value != null) {
            Text(
                text = item.value,
                color = textSecondary,
                fontSize = 16.sp
            )
        } else {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Ir",
                tint = textSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


@Composable
@Preview
fun ProfileContentPreview() {
    ProfileContent(
        profile = profilesDTO.first().toDomain(),
        onContactUsClick = {},
        onHelpClick = {},
        onEditProfileClick = {},
        onRemindersClick = {}
    )
}