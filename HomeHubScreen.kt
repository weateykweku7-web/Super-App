// com/yourname/superapp/feature/hub/HomeHubScreen.kt
package com.yourname.superapp.feature.hub

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class HubFeature(
    val title: String,
    val description: String,
    val route: String
)

@Composable
fun HomeHubScreen(
    onNavigateToRoute: (String) -> Unit,
    onNavigateToDeleteAccount: () -> Unit
) {
    // List of all your major app features
    val features = listOf(
        HubFeature("Downloader", "Download videos & music from links", "downloader"),
        HubFeature("Media Player", "Watch movies & listen to songs", "player"),
        HubFeature("Creator Studio", "Edit photos, videos & AI generation", "editor"),
        HubFeature("Social & Dating", "Chat with foreigners & find matches", "social_dating"),
        HubFeature("App Builder", "Build mini apps inside the app", "app_builder"),
        HubFeature("Settings", "Notifications & Preferences", "settings")
    )

    Scaffold(
        topBar = {
            // Simple top bar with a title and an option to manage account
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Super App Hub", style = MaterialTheme.typography.titleLarge)
                    TextButton(onClick = onNavigateToDeleteAccount) {
                        Text("Account", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(features) { feature ->
                FeatureCard(feature = feature, onClick = { onNavigateToRoute(feature.route) })
            }
        }
    }
}

@Composable
fun FeatureCard(feature: HubFeature, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feature.description,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
