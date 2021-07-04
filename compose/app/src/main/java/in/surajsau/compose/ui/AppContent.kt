package `in`.surajsau.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class NavDestination(val route: String) {
    Details("details"), Home("home");
}

@Composable
fun AppContent() {

    val navController = rememberNavController()

    ComposeTheme {
        NavHost(navController = navController, startDestination = NavDestination.Home.route) {
            composable(NavDestination.Home.route) {

            }
        }
    }
}