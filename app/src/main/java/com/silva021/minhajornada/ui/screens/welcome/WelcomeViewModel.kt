package com.silva021.minhajornada.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.ui.utils.helper.PreferencesManager
import kotlinx.coroutines.launch

class WelcomeViewModel(
    val preferencesManager: PreferencesManager,
) : ViewModel() {

    fun setFlagShowWelcomeScreen() {
        viewModelScope.launch {
            preferencesManager.setWelcomeShown(true)
        }
    }
}
