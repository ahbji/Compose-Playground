package `in`.surajsau.compose.androidx

import androidx.compose.animation.VectorConverter
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.PropKey
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.unit.Dp

data class Size(val width: Dp, val height: Dp) {
    constructor(size: Dp) : this(width = size, height = size)

    fun toFloat() = androidx.compose.ui.geometry.Size(
            width = this.width.value,
            height = this.height.value
    )
}

@Composable
fun rememberColorVectorConverter(): TwoWayConverter<Color, AnimationVector4D> {
    return remember { Color.VectorConverter(ColorSpaces.LinearExtendedSrgb) }
}

val AlignmentVectorConverter: TwoWayConverter<BiasAlignment, AnimationVector2D>
    get() = TwoWayConverter(
            convertToVector = {
                AnimationVector(v1 = it.horizontalBias, v2 = it.verticalBias)
            },
            convertFromVector = {
                BiasAlignment(horizontalBias = it.v1, verticalBias = it.v2)
            }
    )

class AlignmentPropKey() : PropKey<BiasAlignment, AnimationVector2D> {
    override val typeConverter = AlignmentVectorConverter
}