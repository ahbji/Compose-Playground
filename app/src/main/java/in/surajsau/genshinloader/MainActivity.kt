package `in`.surajsau.genshinloader

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import `in`.surajsau.genshinloader.genshin.GenshinLoader
import `in`.surajsau.genshinloader.simple.GenshinSimpleLoader
import `in`.surajsau.genshinloader.ticker.Ticker
import `in`.surajsau.genshinloader.ui.MyApplicationTheme
import androidx.compose.foundation.Text
import androidx.compose.material.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                mainActivity()
            }
        }
    }
}

@Composable
fun mainActivity() {
    Column(modifier = Modifier.fillMaxSize()) {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        mainActivity()
    }
}