package com.example.schrecknet

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class MatrixRainView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.GREEN
        textSize = 40f
        isAntiAlias = true
    }

    private val characters = ('A'..'Z') + ('0'..'9')
    private val rainColumns = mutableListOf<Float>()
    private var speeds = mutableListOf<Float>()
    private var columnCount = 0
    private lateinit var rainPositions: FloatArray
    private lateinit var rainSpeeds: FloatArray

    init {
        initializeRain()
    }

    private fun initializeRain() {
        rainColumns.clear()
        speeds.clear()
        repeat(columnCount) {
            rainColumns.add(Random.nextFloat() * height)
            speeds.add(Random.nextFloat() * 10 + 5)
        }
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        columnCount = (w / paint.textSize).toInt()

        rainPositions = FloatArray(columnCount) { Random.nextFloat() * h }
        rainSpeeds = FloatArray(columnCount) { Random.nextFloat() * 10 + 5 }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.BLACK)

        for (i in 0 until columnCount) {
            val x = i * paint.textSize
            val y = rainPositions[i]

            val character = characters.random().toString()
            canvas.drawText(character, x, y, paint)

            rainPositions[i] += rainSpeeds[i]

            if (rainPositions[i] > height) {
                rainPositions[i] = 0f
            }
        }

        invalidate()
    }
}