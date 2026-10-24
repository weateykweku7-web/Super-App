// com/yourname/superapp/feature/editor/EditorViewModel.kt
package com.yourname.superapp.feature.editor

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EditorUiState())
    val uiState: StateFlow<EditorUiState> = _uiState.asStateFlow()

    fun selectTool(tool: String) {
        _uiState.value = _uiState.value.copy(selectedTool = tool, statusMessage = "Selected tool: $tool")
    }

    fun applyEdit() {
        _uiState.value = _uiState.value.copy(statusMessage = "Effect applied successfully!")
    }
}

data class EditorUiState(
    val selectedTool: String = "Filters",
    val statusMessage: String = "Ready to edit"
)
