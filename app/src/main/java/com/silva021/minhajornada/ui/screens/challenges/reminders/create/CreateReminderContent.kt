package com.silva021.minhajornada.ui.screens.challenges.reminders.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.domain.model.Reminder
import com.silva021.minhajornada.domain.model.ReminderFrequency
import com.silva021.minhajornada.domain.model.Weekday
import com.silva021.minhajornada.ui.components.FrequencyFilter
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.components.WeekDayFilter
import com.silva021.minhajornada.ui.theme.Palette
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReminderContent(
    existingReminder: Reminder? = null,
    onBackPressed: () -> Unit,
    onSave: (Reminder) -> Unit,
) {
    var reminderFrequencySelected by remember {
        mutableStateOf(
            existingReminder?.frequency ?: ReminderFrequency.DAILY
        )
    }

    var weekdaySelected by remember {
        mutableStateOf(
            existingReminder?.weekday ?: Weekday.MONDAY
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.backgroundColor)
    ) {
        Header(
            title = "Novo Lembrete",
            onBackPressed = onBackPressed,
        )

        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            val currentTime = Calendar.getInstance()

            val timePickerState = rememberTimePickerState(
                initialHour = existingReminder?.hour ?: currentTime.get(Calendar.HOUR_OF_DAY),
                initialMinute = existingReminder?.minute ?:currentTime.get(Calendar.MINUTE),
                is24Hour = true,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TimePicker(
                    colors = TimePickerDefaults.colors().copy(
                        containerColor = Color(0xFF121212),
                        clockDialColor = Color(0xFF1E1E1E),
                        clockDialSelectedContentColor = White,
                        clockDialUnselectedContentColor = Color(0xFFA0A0A0),
                        selectorColor = Color(0xFF50D22C),
                        periodSelectorBorderColor = Color(0xFF333333),
                        periodSelectorSelectedContainerColor = Color(0xFF50D22C),
                        periodSelectorUnselectedContainerColor = Color(0xFF1E1E1E),
                        periodSelectorSelectedContentColor = Color.Black,
                        periodSelectorUnselectedContentColor = Color(0xFFA0A0A0),
                        timeSelectorSelectedContainerColor = Color(0xFF50D22C),
                        timeSelectorUnselectedContainerColor = Color(0xFF1E1E1E),
                        timeSelectorSelectedContentColor = Color.Black,
                        timeSelectorUnselectedContentColor = Color(0xFFA0A0A0)
                    ),
                    state = timePickerState,
                )
            }

            Text(
                text = "Frequência",
                color = Palette.textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            FrequencyFilter(
                modifier = Modifier.fillMaxWidth(),
                selectedItem = reminderFrequencySelected,
                onItemSelected = { reminderFrequencySelected = it }
            )

            if (reminderFrequencySelected == ReminderFrequency.WEEKLY) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Dias da Semana",
                    color = Palette.textPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                WeekDayFilter(
                    modifier = Modifier.fillMaxWidth(),
                    selectedItem = weekdaySelected,
                    onItemSelected = { weekdaySelected = it }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Salvar",
                onClick = {
                    onSave(
                        Reminder(
                            id = 1,
                            frequency = reminderFrequencySelected,
                            isActive = true,
                            challengeId = "challengeId_placeholder",
                            weekday = Weekday.SATURDAY,
                            hour = timePickerState.hour,
                            minute = timePickerState.minute
                        )
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateReminderContentPreview() {
    MaterialTheme {
        CreateReminderContent(
            onBackPressed = {},
            onSave = {}
        )
    }
}