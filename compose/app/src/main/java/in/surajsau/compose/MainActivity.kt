package `in`.surajsau.compose

import `in`.surajsau.compose.home.HomeScreen
import `in`.surajsau.compose.screens.GoogleFonts
import `in`.surajsau.compose.screens.MusicPlayer
import `in`.surajsau.compose.screens.NeomorphButton
import `in`.surajsau.compose.screens.Parallax
import `in`.surajsau.compose.screens.todo.TodoList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import `in`.surajsau.compose.ui.ComposeTheme
import androidx.activity.compose.setContent
import androidx.navigation.NavHost

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApp()
        }
    }
}

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    ComposeTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomeScreen(navController) }
            composable("todo") { TodoList() }
            composable("music_player") { MusicPlayer() }
            composable("parallax_scroll") { Parallax() }
            composable("g_fonts") { GoogleFonts() }
            composable("pizza_kit") {}
            composable("neomorph") { NeomorphButton() }
        }
    }
}