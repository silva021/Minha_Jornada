package com.silva021.minhajornada.ui.screens.challenges.reminders

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.Reminder
import com.silva021.minhajornada.domain.model.ReminderFrequency.DAILY
import com.silva021.minhajornada.domain.model.ReminderFrequency.WEEKDAYS
import com.silva021.minhajornada.domain.model.ReminderFrequency.WEEKENDS
import com.silva021.minhajornada.domain.model.ReminderFrequency.WEEKLY
import com.silva021.minhajornada.domain.model.toDomain
import com.silva021.minhajornada.ui.DatabaseFake
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.components.SecondButton
import com.silva021.minhajornada.ui.mockReminders
import com.silva021.minhajornada.ui.theme.Palette
import java.util.Locale

@Composable
fun RemindersContent(
    challenge: Challenge,
    reminders: List<Reminder>,
    onBackPressed: () -> Unit,
    onAddReminderClick: () -> Unit,
    onEditReminder: (Reminder) -> Unit,
    onUpdateReminder: (Reminder) -> Unit,
    onDeleteReminder: (Int) -> Unit,
    onSaveSettingsClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.backgroundColor)
    ) {
        Header(
            title = "Lembretes",
            onBackPressed = onBackPressed
        )
        Column(
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
        ) {
            Text(
                text = "Desafio",
                color = Palette.textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Palette.cardBackground, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(Palette.cardBackground, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = "Desafio de Fitness",
                        tint = Color(0xFF50D22C),
                        modifier = Modifier.size(32.dp)
                    )
                }

                Column {
                    Text(
                        text = challenge.title,
                        color = Palette.textPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = challenge.description,
                        color = Palette.textSecondary,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lembretes",
                        color = Palette.textPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    SecondButton(
                        onClick = onAddReminderClick,
                        shape = RoundedCornerShape(50),
                        text = "Adicionar",
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .background(Palette.cardBackground, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    items(
                        items = reminders,
                        key = { reminder -> reminder.id }
                    ) { reminder ->
                        Log.d("lucas-debug", "Rendering reminder: ${reminder}")
                        SwipeableReminderItem(
                            reminder = reminder,
                            onCardClick = { reminder -> onEditReminder(reminder) },
                            onCheckedChange = { isChecked -> onUpdateReminder(reminder.copy(isActive = isChecked)) },
                            onDeleteClick = {
                                onDeleteReminder.invoke(it)
                            },
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                PrimaryButton(
                    text = "Salvar Configurações",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /* Salvar configurações */ },
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun SwipeableReminderItem(
    reminder: Reminder,
    onCardClick: (Reminder) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteClick: (Int) -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        positionalThreshold = { totalWidth -> totalWidth * 0.5f },
        confirmValueChange = { dismissValue ->
            when (dismissValue) {
                SwipeToDismissBoxValue.EndToStart -> {
                    onDeleteClick(reminder.id)
                    false
                }

                else -> false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF44336))
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        content = {
            ReminderItem(
                reminder = reminder,
                onCheckedChange = onCheckedChange,
                onCardClick = onCardClick
            )
        }
    )
}

@Composable
fun ReminderItem(
    reminder: Reminder,
    onCardClick: (Reminder) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Palette.cardBackground)
            .padding(16.dp)
            .clickable{
                onCardClick(reminder)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            val frequencyText = when (reminder.frequency) {
                DAILY -> "Todos os dias"
                WEEKLY -> reminder.weekday.fullName
                WEEKDAYS -> "Todos os dias úteis"
                WEEKENDS -> "Finais de semana"
            }

            val timeText = String.format(
                Locale.getDefault(),
                "%02d:%02d",
                reminder.hour,
                reminder.minute
            )

            Text(
                text = timeText,
                color = Palette.textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = frequencyText,
                color = Palette.textSecondary,
                fontSize = 14.sp
            )
        }

        Switch(
            checked = reminder.isActive,
            onCheckedChange = { isChecked ->
                onCheckedChange.invoke(isChecked)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Palette.primaryColor,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Palette.cardBackground
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RemindersScreenPreview() {
    MaterialTheme {
        RemindersContent(
            challenge = DatabaseFake.challengesDto.first().toDomain(),
            reminders = mockReminders.map { it.toDomain() },
            onBackPressed = {},
            onAddReminderClick = {},
            onSaveSettingsClick = {},
            onUpdateReminder = {},
            onEditReminder = {},
            onDeleteReminder = {}
        )
    }
}