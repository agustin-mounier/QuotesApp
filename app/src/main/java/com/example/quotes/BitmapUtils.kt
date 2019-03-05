package com.example.quotes

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.edit_quotes_layout.*

class BitmapUtils {

    companion object {

        fun addTextToBitmap(background: Bitmap, textViewContainer: View, vararg textViews: TextView): Bitmap {
            val cs = Bitmap.createBitmap(background.width, background.height, Bitmap.Config.ARGB_8888)
            val comboImage = Canvas(cs)
            comboImage.drawBitmap(background, 0f, 0f, null)

            for (textView in textViews) {
                val textViewBmp = Bitmap.createBitmap(textView.drawingCache)
                val newBmpDimens = scaleDimensToBmp(background, textViewBmp.width, textViewBmp.height, textViewContainer)
                val newPosition = scaleDimensToBmp(background, textView.left, textView.top, textViewContainer)
                val resizedForeground = getResizedBitmap(textViewBmp, newBmpDimens[0], newBmpDimens[1])
                comboImage.drawBitmap(resizedForeground, newPosition[0].toFloat(), newPosition[1].toFloat(), null)
            }

            return cs
        }

        private fun scaleDimensToBmp(bm: Bitmap, xDimenToScale: Int, yDimenToScale: Int, dimensContainer: View): Array<Int> {
            val scaledWidth = (1.0f * bm.width * xDimenToScale / dimensContainer.width).toInt()
            val scaledHeight = (1.0f * bm.height * yDimenToScale / dimensContainer.height).toInt()
            return arrayOf(scaledWidth, scaledHeight)
        }

        private fun getResizedBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
            val width = bm.width
            val height = bm.height
            val scaleWidth = newWidth.toFloat() / width
            val scaleHeight = newHeight.toFloat() / height
            val matrix = Matrix()
            matrix.postScale(scaleWidth, scaleHeight)
            val resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false)
            bm.recycle()
            return resizedBitmap
        }
    }
}