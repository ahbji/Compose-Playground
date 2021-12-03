package `in`.surajsau.compose.ui.samples.single

import `in`.surajsau.compose.R
import android.graphics.Bitmap
import android.view.MotionEvent
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.drawToBitmap

sealed class MagnifyingGlassState {
    object Hide: MagnifyingGlassState()
    data class Show(val x: Float, val y: Float): MagnifyingGlassState()
}

sealed class CropMode {
    data class Partial(val x: Int, val y: Int, val width: Int, val height: Int): CropMode()
    object None: CropMode()
}

@Composable
fun MagnifyImageScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier) {
        Magnify(
            painter = painterResource(id = R.drawable.mizutsune),
            magnificationFactor = 3f,
            magnifierSize = 80.dp,
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Magnify(
    painter: Painter,
    modifier: Modifier = Modifier,
    magnificationFactor: Float = 2f,
    magnifierSize: Dp,
) {

    var magnifyingGlassState by remember { mutableStateOf<MagnifyingGlassState>(MagnifyingGlassState.Hide) }

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    val cropSize = with(LocalDensity.current) { (magnifierSize.toPx()/magnificationFactor).toInt() }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(magnifierSize/2)
            .pointerInteropFilter { event ->
                magnifyingGlassState = when (event.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        MagnifyingGlassState.Show(event.x, event.y)
                    }

                    else -> MagnifyingGlassState.Hide
                }
                return@pointerInteropFilter true
            }
    ) {
        CrpoppedBitmapContainer(
            cropMode = when (magnifyingGlassState) {
                is MagnifyingGlassState.Show -> {
                    val (x, y) = magnifyingGlassState as MagnifyingGlassState.Show
                    CropMode.Partial(x.toInt() - cropSize/2, y.toInt() - cropSize/2, cropSize, cropSize)
                }
                else -> CropMode.None
            },
            onBitmapReady = { bitmap = it }
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    if (magnifyingGlassState is MagnifyingGlassState.Show) {
        val (x, y) = magnifyingGlassState as MagnifyingGlassState.Show

        Row {
            with(LocalDensity.current) {
                Spacer(modifier = Modifier.width(x.toDp()))
            }
            Column {
                with(LocalDensity.current) {
                    Spacer(modifier = Modifier.height(y.toDp()))
                }

                if (bitmap != null) {
                    MagnifyGlass(
                        bitmap = bitmap!!,
                        radius = magnifierSize
                    )
                }
            }
        }
    }
}

@Composable
fun CrpoppedBitmapContainer(
    onBitmapReady: (Bitmap) -> Unit,
    modifier: Modifier = Modifier,
    cropMode: CropMode = CropMode.None,
    bitmapConfig: Bitmap.Config = Bitmap.Config.ARGB_8888,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    val composeView = remember { ComposeView(context) }

    LaunchedEffect(cropMode) {
        val bitmap = composeView.drawToBitmap(config = bitmapConfig)

        when (cropMode) {
            is CropMode.Partial -> {
                val croppedBitmap = Bitmap.createBitmap(
                    bitmap,
                    cropMode.x.coerceIn(0, bitmap.width - cropMode.width),
                    cropMode.y.coerceIn(0, bitmap.height - cropMode.height),
                    cropMode.width,
                    cropMode.height
                )
                onBitmapReady.invoke(croppedBitmap)
            }

            else -> { /* do nothing */ }
        }
    }

    AndroidView(modifier = modifier, factory = {
        composeView.apply {
            setContent { content.invoke() }
        }
    })
}

@Composable
fun MagnifyGlass(
    bitmap: Bitmap,
    modifier: Modifier = Modifier,
    radius: Dp = 10.dp
) {

    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = null,
        modifier = modifier
            .size(radius)
            .clip(CircleShape)
            .border(width = 1.dp, color = Color.LightGray, shape = CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun preview_MagnifyingGlass() {

    val croppedSize = with(LocalDensity.current) { 20.dp.roundToPx() }
    val bitmap = AppCompatResources.getDrawable(LocalContext.current, R.drawable.mizutsune)!!.toBitmap()
    val croppedBitmap = Bitmap.createBitmap(bitmap, 320, 240, croppedSize, croppedSize)

    MagnifyGlass(
        bitmap = croppedBitmap,
        radius = 70.dp
    )
}

@Preview
@Composable
fun preview_Zoomable() {

    Magnify(
        painter = painterResource(id = R.drawable.mizutsune),
        magnifierSize = 20.dp,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}