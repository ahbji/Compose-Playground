package `in`.surajsau.compose.genshin

import `in`.surajsau.compose.simple.GenshinSimpleLoader
import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GenshinScreen() {
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

@Preview
@Composable
fun PreviewGenshinScreen() {
    GenshinScreen()
}