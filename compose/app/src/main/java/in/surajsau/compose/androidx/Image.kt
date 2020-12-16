package `in`.surajsau.compose.androidx

import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.imageResource
import coil.Coil
import coil.request.ImageRequest
import coil.request.ImageResult

@Composable
fun Image(
    url: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {

    WithConstraints(modifier = modifier) {
        val context = AmbientContext.current
        onCommit(url) {

            val imageRequest = ImageRequest.Builder(context = context)
                .data(data = url)
                .listener(object: ImageRequest.Listener {
                    override fun onSuccess(request: ImageRequest, metadata: ImageResult.Metadata) {
                        super.onSuccess(request, metadata)
                    }
                })
            val coil = Coil.imageLoader(context = context)

            onDispose {

            }
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