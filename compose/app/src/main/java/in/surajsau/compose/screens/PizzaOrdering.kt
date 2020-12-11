package `in`.surajsau.compose.screens

import `in`.surajsau.compose.androidx.AlignmentPropKey
import `in`.surajsau.compose.androidx.AlignmentVectorConverter
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.animate
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onActive
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

val buttonWidth = DpPropKey("button_width")
val buttonHeight = DpPropKey("button_height")
val buttonYOffset = DpPropKey("yoffset")
val buttonMarginStart = DpPropKey("button_margin_start")

val toppings = listOf(Topping(name = "Cheese", color = Color.Yellow),
        Topping(name = "Mushroom", color = Color.Blue),
        Topping(name = "Mint", color = Color.Green)
)

@Composable
fun PizzaOrdering(modifier: Modifier = Modifier) {

    WithConstraints(modifier) {
        val w = this.maxWidth
        val h = this.maxHeight

        Box(modifier = modifier) {
            val buttonState = remember { mutableStateOf(0) }
            val toState = (buttonState.value + 1) % 2

            val state = transition(definition = transitionDefinition {
                state(0) {
                    this[buttonWidth] = 48.dp
                    this[buttonYOffset] = -(h/2)
                    this[buttonHeight] = 96.dp
                    this[buttonMarginStart] = 16.dp
                }
                state(1) {
                    this[buttonWidth] = w
                    this[buttonYOffset] = 30.dp
                    this[buttonHeight] = 120.dp
                    this[buttonMarginStart] = 0.dp
                }
                transition(0 to 1, 1 to 0) {
                    buttonWidth using tween(durationMillis = 200, easing = FastOutLinearInEasing)
                    buttonHeight using tween(durationMillis = 200, easing = FastOutLinearInEasing)
                    buttonYOffset using tween(durationMillis = 200, easing = FastOutLinearInEasing)
                    buttonMarginStart using tween(durationMillis = 200, easing = FastOutLinearInEasing)
                }
            }, initState = buttonState.value, toState = toState)

            NeomorphButton(
                    enableAnimation = false,
                    modifier = Modifier
                            .size(size = w * 0.95f)
                            .offset(x = 50.dp)
                            .align(alignment = Alignment.CenterEnd)
            )

            AddButton(
                    state = state,
                    onClick = { buttonState.value = (buttonState.value + 1) % 2 },
                    modifier = Modifier.align(alignment = Alignment.BottomStart)
            )

            ToppingScroll(
                    toppings = toppings,
                    modifier = Modifier.align(alignment = Alignment.BottomStart)
            )

        }
    }
}

@Composable
fun AddButton(
        state: TransitionState,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
) {
    Button(
            onClick = onClick,
            shape = RoundedCornerShape(size = 24.dp),
            modifier = modifier
                    .padding(start = state[buttonMarginStart])
                    .size(width = state[buttonWidth], height = 96.dp)
                    .offset(y = state[buttonYOffset])
    ) {}
}

data class Topping(
        val name: String,
        val color: Color,
        val isAdded: Boolean = false
)

@Composable
fun Pizza(
        toppings: List<Topping>,
        modifier: Modifier = Modifier
) {
    Box(
            modifier = modifier
                    .clip(shape = CircleShape)
                    .size(size = 100.dp)
                    .background(color = Color.Yellow)
    ) {
        toppings.forEach {
            PizzaTopping(topping = it, modifier = Modifier.align(
                    alignment = BiasAlignment(
                            Random.nextDouble(0.0, 1.0).toFloat(),
                            Random.nextDouble(0.0, 1.0).toFloat()
                    )
            ))
        }
    }
}

@Composable
fun PizzaTopping(
        topping: Topping,
        modifier: Modifier = Modifier
) {
    Box(
            modifier = modifier
                    .clip(shape = CircleShape)
                    .size(size = 30.dp)
                    .background(color = topping.color)
    )
}

@Composable
fun ToppingScroll(
        toppings: List<Topping>,
        modifier: Modifier = Modifier,
        onToppingSelected: ((Topping) -> Unit) ? = null
) {
    LazyRowFor(
            items = toppings,
            modifier =  modifier,
            verticalAlignment = Alignment.CenterVertically
    ) {
        ToppingScrollItem(
                topping = it,
                onClicked = onToppingSelected
        )
    }
}

@Composable
fun ToppingScrollItem(
        topping: Topping,
        modifier: Modifier = Modifier,
        onClicked: ((Topping) -> Unit) ? = null
) {
    val isSelected = remember { mutableStateOf(false) }
    Box(modifier = modifier
            .size(size = 72.dp)
            .padding(all = 8.dp)
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .background(color = topping.color)
            .border(
                    width = 6.dp,
                    color = if (isSelected.value) Color.Red else Color.Transparent,
                    shape = RoundedCornerShape(size = 8.dp)
            )
            .clickable(onClick = {
                isSelected.value = !isSelected.value
                onClicked?.invoke(topping.copy(isAdded = isSelected.value))
            })
    )
}

@Preview
@Composable
fun previewPizzaOrdering() {
    PizzaOrdering(modifier = Modifier.fillMaxSize().background(color = NeomorphColor))
}