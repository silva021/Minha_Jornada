package com.silva021.minhajornada.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class CategoryType(
    val label: String,
    val icon: ImageVector,
    val color: Color,
) {
    FITNESS(
        "Fitness",
        Icons.Default.FitnessCenter,
        Color(0xFF4CAF50)
    ),
    READING(
        "Leitura",
        Icons.Default.MenuBook,
        Color(0xFF3F51B5)
    ),
    CULINARY(
        "Culinária",
        Icons.Default.Restaurant,
        Color(0xFFFF7043)
    ),
    NATURE(
        "Natureza",
        Icons.Default.NaturePeople,
        Color(0xFF66BB6A)
    ),
    MINDSET(
        "Mindset",
        icon = Icons.Default.SelfImprovement,
        color = Color(0xFFAB47BC)
    ),
    EDUCATION(
        "Educação",
        icon = Icons.Default.School,
        color = Color(0xFF29B6F6)
    )
}