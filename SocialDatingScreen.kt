// com/yourname/superapp/feature/social/SocialDatingScreen.kt
package com.yourname.superapp.feature.social

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SocialDatingScreen(
    onNavigateToChat: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Global Dating & Chat") },
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
            Text(text = "Discover Foreign Matches", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Swipe profiles or connect globally with real-time translation chat.")
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onNavigateToChat("user_123") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Open Sample Chat")
            }
        }
    }
}
