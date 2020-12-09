package `in`.surajsau.compose.native

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import `in`.surajsau.compose.R

class Icon @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val imagePaint = Paint().apply {
        colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, R.color.purple_200), PorterDuff.Mode.SRC)
    }

    private val overlayPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.teal_700)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        canvas?.let {
            it.drawRect(0f, 0f, 200f, 200f, overlayPaint)
            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.electro)
            it.drawBitmap(bitmap, 0f, 0f, imagePaint)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setMeasuredDimension(200, 200)
    }
}