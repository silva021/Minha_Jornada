package com.silva021.minhajornada.ui.screens.challenges.reminders

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.minhajornada.domain.model.Reminder
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun RemindersScreen(
    viewModel: RemindersViewModel = koinViewModel(),
    challengeId: String,
    onBackPressed: () -> Unit,
    onAddReminderClick: () -> Unit,
    onEditReminder: (String) -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadChallengeById(challengeId)
    }

    when (val state = state) {
        is RemindersUiState.Loading -> LoadingScreen()
        is RemindersUiState.Error -> ErrorScreen(
            onRetry = { viewModel.loadChallengeById(challengeId) },
        )
        is RemindersUiState.Success -> {
            RemindersContent(
                challenge = state.challenge,
//                reminders = viewModel.reminders,
                onBackPressed = onBackPressed,
                onAddReminderClick = onAddReminderClick,
                onUpdateReminder = { reminder ->
//                    viewModel.updateReminder(reminder)
                },
                onDeleteReminder = { reminderId ->
                    viewModel.deleteReminder(reminderId, challengeId)
                },
                onEditReminder = {
                    onEditReminder.invoke(it.id)
                },
            )
        }
    }
}