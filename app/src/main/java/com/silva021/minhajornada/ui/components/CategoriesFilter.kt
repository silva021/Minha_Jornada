package com.silva021.minhajornada.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.primaryColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary

@Composable
fun CategoriesFilter(
    modifier: Modifier = Modifier,
    selectedCategory: CategoryType,
    onCategorySelected: (CategoryType) -> Unit
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())

    ) {
        CategoryType.entries.forEach { category ->
            FilterChip(
                selected = category == selectedCategory,
                onClick = { onCategorySelected(category) },
                label = { Text(category.label) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = primaryColor,
                    selectedLabelColor = backgroundColor,
                    containerColor = cardBackground,
                    labelColor = textPrimary
                ),
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoriesFilterPreview() {
    CategoriesFilter(
        selectedCategory = CategoryType.EDUCATION,
        onCategorySelected = {}
    )
}