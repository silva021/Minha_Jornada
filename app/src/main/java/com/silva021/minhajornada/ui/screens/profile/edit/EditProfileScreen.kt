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
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    onEditProfilePicture: () -> Unit,
    onSaveProfileChanges: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
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
