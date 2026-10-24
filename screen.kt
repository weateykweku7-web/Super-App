// com/yourname/superapp/navigation/Screen.kt
package com.yourname.superapp.navigation

sealed class Screen(val route: String) {
    // Auth
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object DeleteAccount : Screen("delete_account")

    // Main App Sections
    object HomeHub : Screen("home_hub") // Dashboard for downloader, movies, etc.
    object Downloader : Screen("downloader")
    object Player : Screen("player")
    object Editor : Screen("editor")
    object AiCreator : Screen("ai_creator")
    object SocialDating : Screen("social_dating")
    object Chat : Screen("chat/{userId}") {
        fun createRoute(userId: String) = "chat/$userId"
    }
    object AppBuilder : Screen("app_builder")
    object Settings : Screen("settings")
}
