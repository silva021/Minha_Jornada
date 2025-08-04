package com.silva021.minhajornada.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.minhajornada.domain.model.CheckInStatus
import com.silva021.minhajornada.domain.model.CheckInStatus.FAILURE
import com.silva021.minhajornada.domain.model.CheckInStatus.SUCCESS
import com.silva021.minhajornada.ui.theme.Palette.accentColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground

@Composable
fun StatusFilter(
    modifier: Modifier = Modifier,
    checkInStatusSelected: CheckInStatus,
    onCheckInStatusSelected: (CheckInStatus) -> Unit,
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())

    ) {
        CheckInStatus.entries.forEach { checkInStatus ->
            FilterChip(
                selected = checkInStatus == checkInStatusSelected,
                onClick = { onCheckInStatusSelected(checkInStatus) },
                leadingIcon = {
                    Icon(
                        imageVector = when (checkInStatus) {
                            SUCCESS -> Icons.Default.Check
                            FAILURE -> Icons.Default.Error
                        },
                        contentDescription = null,
                        tint = White
                    )
                },
                label = { Text(checkInStatus.label) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = when (checkInStatus) {
                        SUCCESS -> Color(0xFF00796B)
                        FAILURE -> Color(0xFFC62828)
                    },
                    selectedLabelColor = White,
                    containerColor = cardBackground,
                    labelColor = White
                ),
                modifier = Modifier
                    .height(40.dp)
                    .padding(end = 8.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StatusFilterPreview() {
    StatusFilter(
        checkInStatusSelected = CheckInStatus.SUCCESS,
        onCheckInStatusSelected = {}
    )
}