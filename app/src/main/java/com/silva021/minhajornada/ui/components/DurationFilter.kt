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
import com.silva021.minhajornada.domain.model.DurationType
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.primaryColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary

@Composable
fun DurationFilter(
    modifier: Modifier = Modifier,
    selectedDuration: DurationType,
    onDurationSelected: (DurationType) -> Unit
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())

    ) {
        DurationType.entries.forEach { duration ->
            FilterChip(
                selected = duration == selectedDuration,
                onClick = { onDurationSelected(duration) },
                label = { Text(duration.label) },
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
fun DurationFilterPreview() {
    DurationFilter(
        selectedDuration = DurationType.THREE_DAYS,
        onDurationSelected = {}
    )
}