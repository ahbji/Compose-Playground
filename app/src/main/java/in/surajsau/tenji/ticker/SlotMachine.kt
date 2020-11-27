package `in`.surajsau.tenji.ticker

import `in`.surajsau.tenji.androidx.RadialShadow
import `in`.surajsau.tenji.androidx.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun SlotMachine() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .align(alignment = Alignment.Center)
        ) {
            SlotMachineColumn(from = 0, to = 1)
            SlotMachineColumn(from = 0, to = 5)
            SlotMachineColumn(from = 0, to = 9)
        }
    }
}

@Composable
fun SlotMachineColumn(
    from: Int,
    to: Int,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .height(height = 50.dp)
            .clipToBounds()
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            RadialShadow(
                size = Size(
                    width = 100.dp,
                    height = 25.dp
                ),
                shadowColor = Color.LightGray,
                modifier = Modifier
                    .drawLayer(rotationZ = -180f)
            )

            RadialShadow(
                size = Size(
                    width = 100.dp,
                    height = 25.dp
                ),
                shadowColor = Color.LightGray,
            )
        }

        Ticker(
            from = from,
            to = to,
            modifier = Modifier
                .align(alignment = Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PreviewSlotMachine() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize()
        ) {
            SlotMachine()
        }
    }
}