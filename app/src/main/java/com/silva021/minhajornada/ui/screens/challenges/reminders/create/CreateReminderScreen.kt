package com.silva021.minhajornada.ui.screens.challenges.reminders.create

import androidx.compose.runtime.Composable
import com.silva021.minhajornada.ui.screens.challenges.reminders.RemindersViewModel

@Composable
fun CreateReminderScreen(
    viewModel: RemindersViewModel,
    onBackPressed: () -> Unit ,
) {
    CreateReminderContent(
        onBackPressed = onBackPressed,
        existingReminder = null,
        onSave = {
            viewModel.addReminder(it).also {
                onBackPressed()
            }
        }
    )
}