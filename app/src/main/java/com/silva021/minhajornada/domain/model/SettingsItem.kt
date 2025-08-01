package com.silva021.minhajornada.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsItem(
    val text: String,
    val value: String? = null,
    val icon: ImageVector? = null,
)