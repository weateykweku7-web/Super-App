// com/yourname/superapp/feature/settings/SettingsViewModel.kt
package com.yourname.superapp.feature.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun toggleNotifications(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(notificationsEnabled = enabled)
    }

    fun toggleDarkMode(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(darkModeEnabled = enabled)
    }

    fun toggleLocationMatching(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(locationMatchingEnabled = enabled)
    }
}

data class SettingsUiState(
    val notificationsEnabled: Boolean = true,
    val darkModeEnabled: Boolean = true,
    val locationMatchingEnabled: Boolean = true
)
