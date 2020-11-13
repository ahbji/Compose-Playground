package `in`.surajsau.genshinloader

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import `in`.surajsau.genshinloader.icon.GenshinLoader
import `in`.surajsau.genshinloader.simple.GenshinSimpleLoader
import `in`.surajsau.genshinloader.ui.MyApplicationTheme
import androidx.compose.foundation.Text

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
    val slider = remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
                text = "Simple",
                modifier = Modifier.padding(bottom = 8.dp)
        )
        GenshinSimpleLoader(progress = slider.value)

        Spacer(modifier = Modifier.height(height = 16.dp))

        Text(
                text = "Canvas",
                modifier = Modifier.padding(bottom = 8.dp)
        )
        GenshinLoader(progress = slider.value)

        Slider(
            value = slider.value,
            valueRange = 0f..100f,
            onValueChange = {
                Log.e("Slider", "$it")
                slider.value = it
            },
            steps = 1,
            modifier = Modifier
                .width(width = 300.dp)
                .padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        mainActivity()
    }
}