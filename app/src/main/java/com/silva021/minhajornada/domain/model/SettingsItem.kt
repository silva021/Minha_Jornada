package com.silva021.minhajornada.domain.model

data class SettingsItem(
    val text: String,
    val value: String? = null,
    val onClick: (() -> Unit)? = null
)