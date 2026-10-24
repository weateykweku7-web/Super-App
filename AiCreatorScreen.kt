// com/yourname/superapp/feature/aicreator/AiCreatorScreen.kt
package com.yourname.superapp.feature.aicreator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AiCreatorScreen(
    viewModel: AiCreatorViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Video Generator") },
                navigationIcon = { TextButton(onClick = onNavigateBack) { Text("Back") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Text-to-Video & Photo-to-Video", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.prompt,
                onValueChange = { viewModel.onPromptChanged(it) },
                label = { Text("Describe the video you want to generate...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                enabled = !state.isGenerating
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.generateVideo() },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isGenerating && state.prompt.isNotBlank()
            ) {
                Text(if (state.isGenerating) "Generating AI Video..." else "Generate Video")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (state.isGenerating) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(text = state.status, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
