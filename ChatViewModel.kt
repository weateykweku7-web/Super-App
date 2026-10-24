// com/yourname/superapp/feature/social/ChatViewModel.kt
package com.yourname.superapp.feature.social

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Message(
    val id: String,
    val sender: String,
    val text: String,
    val isMe: Boolean
)

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(
        listOf(
            Message("1", "Foreign Friend", "Hello! Excited to chat across borders.", false),
            Message("2", "You", "Hi! Glad to connect using the super app.", true)
        )
    )
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    fun sendMessage(text: String) {
        if (text.isBlank()) return
        val newMsg = Message(
            id = System.currentTimeMillis().toString(),
            sender = "You",
            text = text,
            isMe = true
        )
        _messages.value = _messages.value + newMsg
    }
}
