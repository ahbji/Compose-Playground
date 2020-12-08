package `in`.surajsau.tenji.bottombar

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.genshinloader.R

val purpleDark = Color(0xFF5B38B7)
val purpleLight = Color(0xFFE0D7F3)
val yellowDark = Color(0xFFE7A919)
val yellowLight = Color(0xFFFCEFD3)
val greenDark = Color(0xFF1897A6)
val greenLight = Color(0xFFD4EBEF)
val pinkDark = Color(0xFFC9389D)
val pinkLight = Color(0xFFF7D7EF)

enum class TabState {
    OPENED, CLOSED
}

val textOpacity = FloatPropKey("text_opacity")
val tabWidth = DpPropKey("tab_width")

data class Tab(
    val colorDark: Color,
    val colorLight: Color,
    val text: String,
    val isOpened: Boolean = false
)
val Home = Tab(purpleDark, purpleLight, "Home")
val Favorite = Tab(yellowDark, yellowLight, "Favorite")
val Search = Tab(greenDark, greenLight, "Search")
val Account = Tab(pinkDark, pinkLight, "Account")

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .height(height = 75.dp)
            .background(color = Color.White)
    ) {
        val buttons = remember { mutableStateListOf(Home, Favorite, Search, Account) }
        buttons
            .forEach { tab ->
                val currentState = if(tab.isOpened) TabState.OPENED else TabState.CLOSED
                val fromState = remember { mutableStateOf(currentState) }
                val toState = if (fromState.value == TabState.CLOSED) {
                    TabState.OPENED
                } else {
                    TabState.CLOSED
                }

                val transition = transition(
                    definition = transitionDefinition {
                        state(TabState.OPENED) {
                            this[textOpacity] = 1f
                            this[tabWidth] = 100.dp
                        }

                        state(TabState.CLOSED) {
                            this[textOpacity] = 0f
                            this[tabWidth] = 50.dp
                        }

                        transition(TabState.CLOSED to TabState.OPENED) {
                            textOpacity using tween()
                        }

                        transition(TabState.OPENED to TabState.CLOSED) {
                            textOpacity using tween()
                        }
                    },
                    initState = currentState,
                    toState = toState
                )

                Tab(
                    state = transition,
                    asset = vectorResource(id = R.drawable.ic_paper_plane),
                    text = tab.text,
                    textColor = tab.colorDark,
                    backgroundColor = tab.colorLight,
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    onClick = {
                        buttons[buttons.indexOf(tab)] = tab.copy(isOpened = !tab.isOpened)
                    }
                )
            }
    }
}

@Composable
fun Tab(
    state: TransitionState,
    asset: ImageVector,
    text: String,
    textColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Button(
        onClick = { onClick?.invoke() },
        colors = ButtonConstants.defaultButtonColors(
            backgroundColor = backgroundColor,
        ),
        elevation = ButtonConstants.defaultElevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
        shape = RoundedCornerShape (corner = CornerSize(size = 25.dp)),
        modifier = modifier
            .size(width = state[tabWidth], height = 50.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier.width(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = asset,  // new code
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                softWrap = false,
                color = textColor
            )
        }
    }
}

@Preview
@Composable
private fun previewBottomBar() {
    Box(modifier = Modifier.fillMaxSize().background(color = Color.Gray)) {
        BottomBar(modifier = Modifier.align(alignment = Alignment.BottomCenter))
    }
}