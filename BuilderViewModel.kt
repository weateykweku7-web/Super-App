// com/yourname/superapp/feature/builder/BuilderViewModel.kt
package com.yourname.superapp.feature.builder

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BuilderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BuilderUiState())
    val uiState: StateFlow<BuilderUiState> = _uiState.asStateFlow()

    fun onCodeChanged(newCode: String) {
        _uiState.value = _uiState.value.copy(codeSnippet = newCode)
    }

    fun compileAndRunApp() {
        _uiState.value = _uiState.value.copy(
            buildStatus = "Compiling sandbox container...",
            isCompiled = true
        )
    }
}

data class BuilderUiState(
    val codeSnippet: String = "// Write or generate layout code here\nColumn {\n    Text(\"Hello from My Mini App!\")\n}",
    val buildStatus = "Sandbox ready",
    val isCompiled: Boolean = false
)
