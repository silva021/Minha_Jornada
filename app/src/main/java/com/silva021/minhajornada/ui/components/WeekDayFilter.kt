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
import com.silva021.minhajornada.domain.model.Weekday
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.primaryColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary

@Composable
fun WeekDayFilter(
    modifier: Modifier = Modifier,
    selectedItem: Weekday,
    onItemSelected: (Weekday) -> Unit
) {
    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
        Weekday.entries.forEach { item ->
            FilterChip(
                selected = item == selectedItem,
                onClick = { onItemSelected(item) },
                label = { Text(item.shortName) },
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
fun WeekDayFilterPreview() {
    WeekDayFilter(
        selectedItem = Weekday.THURSDAY,
        onItemSelected = {}
    )
}