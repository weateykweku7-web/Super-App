// com/yourname/superapp/feature/settings/SettingsScreen.kt
package com.yourname.superapp.feature.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel(),
    onNavigateToAccountDeletion: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings & Notifications") },
                navigationIcon = { TextButton(onClick = onNavigateBack) { Text("Back") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Preferences", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)

            // Notifications Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enable Push Notifications")
                Switch(
                    checked = state.notificationsEnabled,
                    onCheckedChange = { viewModel.toggleNotifications(it) }
                )
            }

            // Dark Mode Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Dark Theme Mode")
                Switch(
                    checked = state.darkModeEnabled,
                    onCheckedChange = { viewModel.toggleDarkMode(it) }
                )
            }

            // Global Dating Location Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Global Dating Location Match")
                Switch(
                    checked = state.locationMatchingEnabled,
                    onCheckedChange = { viewModel.toggleLocationMatching(it) }
                )
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Account Security / Deletion option link
            Button(
                onClick = onNavigateToAccountDeletion,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Manage / Delete Account", color = MaterialTheme.colorScheme.onErrorContainer)
            }
        }
    }
}
