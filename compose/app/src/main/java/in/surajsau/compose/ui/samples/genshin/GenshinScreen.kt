package `in`.surajsau.compose.ui.samples.genshin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
            onValueChange = { slider.value = it },
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