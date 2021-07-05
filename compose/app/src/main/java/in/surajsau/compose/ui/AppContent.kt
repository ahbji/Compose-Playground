package `in`.surajsau.compose.ui

import `in`.surajsau.compose.ui.screen.Details
import `in`.surajsau.compose.ui.screen.Home
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
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
                Home(
                    navigateToDetails = {
                        navController.navigate(
                            "${NavDestination.Details.route}/${it}",
                        )
                    }
                )
            }

            composable(
                "${NavDestination.Details.route}/{index}",
                arguments = listOf(navArgument("index", builder = { type = NavType.IntType }))
            ) {
                Details(index = it.arguments?.getInt("index") ?: 0)
            }
        }
    }
}