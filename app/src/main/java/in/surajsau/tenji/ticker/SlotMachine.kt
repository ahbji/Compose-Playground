package `in`.surajsau.tenji.ticker

import `in`.surajsau.tenji.androidx.RadialShadow
import `in`.surajsau.tenji.androidx.Size
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
fun SlotMachine() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val previous = mutableStateOf(0)
        val random = mutableStateOf(0)

        val c1 = derivedStateOf {
            Pair((previous.value / 100), (random.value / 100))
        }

        val c2 = derivedStateOf {
            Pair((previous.value/10)%10, (random.value/10)%10)
        }

        val c3 = derivedStateOf {
            Pair(previous.value%10, random.value%10)
        }

        Column(
                modifier = Modifier
                        .align(alignment = Alignment.Center)
        ) {
            Row {
                SlotMachineColumn(value = c1)
                SlotMachineColumn(value = c2)
                SlotMachineColumn(value = c3)
            }
            
            Spacer(modifier = Modifier.height(height = 16.dp))
            
            Button(
                    onClick = {
                        previous.value = random.value
                        random.value = Random.nextInt(from = 100, until = 999)
                    },
                    modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(text = "Rotate")
            }
        }
    }
}

@Composable
fun SlotMachineColumn(
    value: State<Pair<Int, Int>>,
    modifier: Modifier = Modifier
) {
    val (from, to) = value.value
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
                    .align(alignment = Alignment.Center),
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