package `in`.surajsau.compose.ui.samples.neomorph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NeomorphScreen() {

    Box(modifier = Modifier.fillMaxSize()) {

        NeomorphButton(
            modifier = Modifier.size(200.dp)
                .align(Alignment.Center)
        )
    }
}