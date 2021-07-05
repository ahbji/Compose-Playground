package `in`.surajsau.compose.ui.component

import androidx.compose.animation.VectorConverter
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces

@Composable
fun rememberColorVectorConverter(): TwoWayConverter<Color, AnimationVector4D> {
    return remember { Color.VectorConverter(ColorSpaces.LinearExtendedSrgb) }
}