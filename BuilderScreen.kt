// com/yourname/superapp/feature/builder/BuilderScreen.kt
package com.yourname.superapp.feature.builder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BuilderScreen(
    viewModel: BuilderViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("In-App App Builder Sandbox") },
                navigationIcon = { TextButton(onClick = onNavigateBack) { Text("Back") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Code Input Section
            OutlinedTextField(
                value = state.codeSnippet,
                onValueChange = { viewModel.onCodeChanged(it) },
                label = { Text("App Code / Script Sandbox") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { viewModel.compileAndRunApp() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Compile & Run Mini App")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Sandbox Preview Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                if (state.isCompiled) {
                    Text(text = "Output: Hello from My Mini App!", color = MaterialTheme.colorScheme.primary)
                } else {
                    Text(text = "Status: ${state.buildStatus}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
