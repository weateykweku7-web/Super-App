import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AdminViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    
    private val _pendingUsers = MutableStateFlow<List<UserProfile>>(emptyList())
    val pendingUsers: StateFlow<List<UserProfile>> = _pendingUsers

    init {
        fetchPendingUsers()
    }

    fun fetchPendingUsers() {
        viewModelScope.launch {
            try {
                val snapshot = db.collection("users")
                    .whereEqualTo("status", "pending")
                    .get()
                    .await()
                
                val users = snapshot.toObjects(UserProfile::class.java)
                _pendingUsers.value = users
            } catch (e: Exception) {
                // Handle error if needed
            }
        }
    }

    fun approveUser(userId: String) {
        viewModelScope.launch {
            try {
                db.collection("users").document(userId)
                    .update("status", "approved")
                    .await()
                
                // Refresh the list after approval
                fetchPendingUsers()
            } catch (e: Exception) {
                // Handle error if needed
            }
        }
    }
}
