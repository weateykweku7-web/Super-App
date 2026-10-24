// com/yourname/superapp/feature/downloader/DownloaderScreen.kt
package com.yourname.superapp.feature.downloader

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DownloaderScreen(
    viewModel: DownloaderViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    var urlInput by remember { mutableStateOf("") }
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Media Downloader") },
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
            OutlinedTextField(
                value = urlInput,
                onValueChange = { urlInput = it },
                label = { Text("Paste Video or Song Link") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.downloadMedia(urlInput) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading && urlInput.isNotBlank()
            ) {
                Text(if (state.isLoading) "Processing..." else "Start Download")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (state.isLoading) {
                LinearProgressIndicator(
                    progress = { state.progress },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(text = state.status, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
