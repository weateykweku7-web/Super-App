// com/yourname/superapp/feature/editor/EditorScreen.kt
package com.yourname.superapp.feature.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EditorScreen(
    viewModel: EditorViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val tools = listOf("Filters", "Crop", "Trim Video", "Audio Overlay", "Adjustments")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Photo & Video Studio") },
                navigationIcon = { TextButton(onClick = onNavigateBack) { Text("Back") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Media Preview Area Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Preview Canvas\n(${state.selectedTool} Mode)", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Editing Tools Toolbar Selection
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                tools.forEach { tool ->
                    Button(
                        onClick = { viewModel.selectTool(tool) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (state.selectedTool == tool) MaterialTheme.colorScheme.primary 
                                             else MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Text(tool, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.applyEdit() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save / Export Edited Media")
            }
        }
    }
}
