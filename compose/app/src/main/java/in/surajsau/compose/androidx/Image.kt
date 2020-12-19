package `in`.surajsau.compose.androidx

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.res.imageResource
import androidx.core.graphics.drawable.toBitmap
import coil.Coil
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.size.Scale
import coil.target.Target

@Composable
fun AsyncImage(
    url: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {

    WithConstraints(modifier = modifier) {
        val width = this.maxWidth
        val height = this.maxHeight
        val context = AmbientContext.current
        val density = AmbientDensity.current

        val imageAsset = remember { mutableStateOf<ImageBitmap?>(null) }

        onCommit(url) {

            val imageRequest = ImageRequest.Builder(context = context)
                .data(data = url)
                .scale(scale = Scale.FILL)
                .size(
                    width = with(density) { width.toIntPx() },
                    height = with(density) { height.toIntPx() }
                )
                .target(object: Target {
                    override fun onSuccess(result: Drawable) {
                        super.onSuccess(result)
                        imageAsset.value = result.toBitmap().asImageBitmap()
                    }
                })
                .build()

            val disposable = Coil.imageLoader(context = context)
                .enqueue(request = imageRequest)

            onDispose {
                disposable.dispose()
            }
        }

        imageAsset.value?.let {
            androidx.compose.foundation.Image(
                bitmap = it,
                alignment = alignment,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = colorFilter
            )
        }
    }
}

@Composable
fun Image(
    resourceId: Int,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {
    androidx.compose.foundation.Image(
        bitmap = imageResource(id = resourceId),
        modifier = modifier,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}