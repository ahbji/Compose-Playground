package `in`.surajsau.tenji

import `in`.surajsau.tenji.neomorph.NeomorphButton
import `in`.surajsau.tenji.neomorph.NeomorphColor
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
import androidx.compose.foundation.background

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
    Column(modifier = Modifier.fillMaxSize().background(
            color = NeomorphColor
    )) {
        NeomorphButton()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        mainActivity()
    }
}