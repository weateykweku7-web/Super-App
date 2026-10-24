// com/yourname/superapp/feature/player/PlayerScreen.kt
package com.yourname.superapp.feature.player

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class MediaItem(val id: String, val title: String, val type: String)

@Composable
fun PlayerScreen(onNavigateBack: () -> Unit) {
    val sampleMedia = listOf(
        MediaItem("1", "Downloaded Music Track 1", "Audio"),
        MediaItem("2", "Action Movie HD", "Video"),
        MediaItem("3", "Downloaded Podcast Episode", "Audio")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movies & Music Player") },
                navigationIcon = { TextButton(onClick = onNavigateBack) { Text("Back") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sampleMedia) { media ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* TODO: Launch ExoPlayer / video player activity */ }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = media.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Type: ${media.type}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
