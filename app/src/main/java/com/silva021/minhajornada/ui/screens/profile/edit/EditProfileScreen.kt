package com.silva021.minhajornada.ui.screens.profile.edit

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import com.silva021.minhajornada.ui.screens.profile.ProfileUiState
import com.silva021.minhajornada.ui.screens.profile.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: ProfileViewModel,
    onEditProfilePicture: () -> Unit,
    onSaveProfileChanges: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        if (viewModel.uiState.value is ProfileUiState.Loading)
            viewModel.fetchProfile()
    }

    when (val state = uiState) {
        is ProfileUiState.Loading -> LoadingScreen()
        is ProfileUiState.Error -> ErrorScreen(
            onRetry = { viewModel.fetchProfile() }
        )

        is ProfileUiState.Success -> EditProfileContent(
            profile = state.profile,
            onEditProfilePicture = onEditProfilePicture,
            onSaveProfileChanges = onSaveProfileChanges,
            onBackPressed = onBackPressed
        )
    }
}
