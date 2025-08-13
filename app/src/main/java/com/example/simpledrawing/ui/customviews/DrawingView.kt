package com.example.simpledrawing.ui.customviews

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context,attrs : AttributeSet?) : View(context,attrs) {
    private var drawPath: Path = Path() // Holds the path being drawn
    private var drawPaint: Paint = Paint() // Paint style
    private var canvasPaint: Paint = Paint(Paint.DITHER_FLAG) // For smooth drawing
    private lateinit var drawCanvas: Canvas // Canvas to draw onto
    private lateinit var canvasBitmap: Bitmap // Bitmap to store drawings
    var onBitmapChanged: ((Bitmap) -> Unit)? = null

    init {
        setupPaint()
    }

    // Configure paint brush properties
    private fun setupPaint() {
        drawPaint.color = Color.WHITE
        drawPaint.isAntiAlias = true
        drawPaint.strokeWidth = 6f
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND
    }
    // When view size changes, prepare a new bitmap & canvas
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(canvasBitmap)
    }
    // Draw current path & saved bitmap
    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(canvasBitmap, 0f, 0f, canvasPaint)
        canvas.drawPath(drawPath, drawPaint)
    }
    // Handle finger touch events
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x
        val touchY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> drawPath.moveTo(touchX, touchY)
            MotionEvent.ACTION_MOVE -> drawPath.lineTo(touchX, touchY)
            MotionEvent.ACTION_UP -> {
                drawCanvas.drawPath(drawPath, drawPaint)
                drawPath.reset()

                // Notify bitmap changed
                onBitmapChanged?.invoke(getBitmap())
            }
        }
        invalidate()
        return true
    }
    // Clear the canvas
    fun clearCanvas() {
        canvasBitmap.eraseColor(Color.TRANSPARENT)
        invalidate()
    }

    // Return the drawn bitmap
    fun getBitmap(): Bitmap {
        return canvasBitmap
    }
    fun setBitmap(bitmap: Bitmap) {
        // Copy bitmap so it’s mutable
        canvasBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        drawCanvas = Canvas(canvasBitmap)
        invalidate()
    }
}