package `in`.surajsau.tenji.dragdrop

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.Spring.DefaultDisplacementThreshold
import androidx.compose.animation.core.Spring.StiffnessHigh
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DragAndDrop() {
        Column(modifier = Modifier.size(300.dp).background(Color.Red)) {
            val x = animatedFloat(150f)
            val y = animatedFloat(150f)
            val bouncySpring = SpringSpec(
                    dampingRatio = DampingRatioMediumBouncy,
                    stiffness = StiffnessLow,
                    visibilityThreshold = DefaultDisplacementThreshold)
            val stiffSpring = SpringSpec(
                    dampingRatio = DampingRatioLowBouncy,
                    stiffness = StiffnessHigh,
                    visibilityThreshold = DefaultDisplacementThreshold)
            Box(modifier = Modifier
                    .offset(
                            with(DensityAmbient.current) { x.value.toDp() },
                            with(DensityAmbient.current) { y.value.toDp() })
                    .size(10.dp)
                    .background(Color.Blue)
                    .dragGestureFilter(
                            object : DragObserver {
                                override fun onDrag(dragDistance: Offset): Offset {
                                    x.animateTo(
                                            targetValue = x.targetValue + dragDistance.x,
                                            anim = stiffSpring
                                    )
                                    y.animateTo(
                                            targetValue = y.targetValue + dragDistance.y,
                                            anim = stiffSpring
                                    )
                                    return super.onDrag(dragDistance)
                                }
                                override fun onStop(velocity: Offset) {
                                    super.onStop(velocity)
                                    reset()
                                }
                                override fun onCancel() {
                                    super.onCancel()
                                    reset()
                                }
                                private fun reset() {
                                    x.animateTo(
                                            targetValue = 150f,
                                            anim = bouncySpring)
                                    y.animateTo(
                                            targetValue = 150f,
                                            anim = bouncySpring)
                                }
                            },
                            startDragImmediately = true
                    )
            )
        }
}

@Composable
@Preview
private fun previewDragAndDrop() {
    Box(modifier = Modifier.fillMaxSize()) {
        DragAndDrop()
    }
}