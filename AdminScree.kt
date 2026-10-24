import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(viewModel: AdminViewModel = viewModel()) {
    val pendingUsers by viewModel.pendingUsers.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Admin Panel - Pending Approvals") }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (pendingUsers.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No pending user requests right now!", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(pendingUsers) { user ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(16.dp)
                            ) {
                                Text(text = user.email, style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Phone: ${user.phoneNumber}", style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Status: ${user.status}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                                
                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    // Approve Button
                                    Button(
                                        modifier = Modifier.weight(1f),
                                        onClick = { viewModel.approveUser(user.userId) }
                                    ) {
                                        Text("Approve")
                                    }

                                    // WhatsApp Direct Chat Button
                                    OutlinedButton(
                                        modifier = Modifier.weight(1f),
                                        onClick = {
                                            val message = "Hello, your Android-Super-App account has been approved!"
                                            val url = "https://wa.me/${user.phoneNumber}?text=${Uri.encode(message)}"
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                            context.startActivity(intent)
                                        }
                                    ) {
                                        Text("💬 WhatsApp")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
