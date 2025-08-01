package com.silva021.minhajornada.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.silva021.minhajornada.data.dto.CategoryDTO
import com.silva021.minhajornada.data.dto.CommunitiesDTO
import com.silva021.minhajornada.data.dto.CommunityDTO

data class Communities(
    val featured: List<Community>,
    val categories: List<Category>,
)

data class Community(
    val id: String,
    val imageUrl: String,
    val name: String,
    val members: String,
)

data class Category(
    val id: String,
    val type: CategoryType,
    val name: String,
)

enum class CategoryType(
    val icon: ImageVector,
    val color: Color,
) {
    FITNESS(
        Icons.Default.FitnessCenter,
        Color(0xFF4CAF50)
    ),
    READING(
        Icons.Default.MenuBook,
        Color(0xFF3F51B5)
    ),
    CULINARY(
        Icons.Default.Restaurant,
        Color(0xFFFF7043)
    ),
    NATURE(
        Icons.Default.NaturePeople,
        Color(0xFF66BB6A)
    )
}

fun CommunitiesDTO.toDomain(): Communities {
    return Communities(
        featured = featured.map { it.toDomain() },
        categories = categories.map { it.toDomain() }
    )
}

fun CommunityDTO.toDomain() = Community(
    id = id,
    imageUrl = imageUrl,
    name = name,
    members = "$members membros"
)

fun CategoryDTO.toDomain() = Category(
    id = id,
    type = CategoryType.valueOf(type.uppercase()),
    name = name
)