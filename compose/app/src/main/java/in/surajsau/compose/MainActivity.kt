package `in`.surajsau.compose

import `in`.surajsau.compose.home.HomeScreen
import `in`.surajsau.compose.screens.GoogleFonts
import `in`.surajsau.compose.screens.MusicPlayer
import `in`.surajsau.compose.screens.NeomorphButton
import `in`.surajsau.compose.screens.Parallax
import `in`.surajsau.compose.todo.TodoList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import `in`.surajsau.compose.ui.ComposeTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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