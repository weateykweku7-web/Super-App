import com.yourname.superapp.feature.builder.BuilderScreen
import com.yourname.superapp.feature.settings.SettingsScreen

// Inside your NavHost:
composable(Screen.AppBuilder.route) {
    BuilderScreen(onNavigateBack = { navController.popBackStack() })
}

composable(Screen.Settings.route) {
    SettingsScreen(
        onNavigateToAccountDeletion = { navController.navigate(Screen.DeleteAccount.route) },
        onNavigateBack = { navController.popBackStack() }
    )
}// Inside your AppNavGraph.kt where you set up your NavHost
composable("admin_screen") {
    val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email

    if (currentUserEmail == "weateykweku7@gmail.com") {
        // Open AdminScreen if it's you
        AdminScreen()
    } else {
        // Block anyone else and show access denied
        AccessDeniedScreen()
    }
}

