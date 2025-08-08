package com.silva021.minhajornada.ui.screens.challenges.reminders.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateReminderScreen(
    viewModel: CreateReminderViewModel = koinViewModel(),
    reminderId: String? = null,
    challengeId: String,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {

        viewModel.getReminderById(
            challengeId = challengeId,
            reminderId = reminderId
        )

        viewModel.eventFlow.collect { event ->
            when (event) {
                is NavigationEvent.NavigateToReminderList -> {
                    onBackPressed.invoke()
                }
            }
        }
    }


    when (val uiState = uiState) {
        is CreateRemindersUiState.Error -> {
            ErrorScreen(
                onRetry = {

                }
            )
        }

        is CreateRemindersUiState.Loading -> {
            LoadingScreen()
        }

        is CreateRemindersUiState.Idle -> {
            CreateReminderContent(
                onBackPressed = onBackPressed,
                existingReminder = uiState.reminder,
                onSave = {
                    viewModel.addsRemindersInChallenge(
                        challengeId = challengeId,
                        reminder = it
                    )
                }
            )
        }
    }
}