package `in`.surajsau.tenji

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import `in`.surajsau.tenji.ticker.Ticker
import `in`.surajsau.tenji.ticker.currentTickerValue
import `in`.surajsau.tenji.ui.MyApplicationTheme
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition

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
    Column(modifier = Modifier.fillMaxWidth()) {

        Ticker(
                state = transition(
                        definition = transitionDefinition {
                            state(0) { this[currentTickerValue] = 0f }
                            state(1) { this[currentTickerValue] = 50f }

                            transition(0 to 1) {
                                currentTickerValue using tween(
                                        durationMillis = 10 * 1_000,
                                        easing = LinearEasing
                                )
                            }
                        },
                        initState = 0,
                        toState = 1
                )
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