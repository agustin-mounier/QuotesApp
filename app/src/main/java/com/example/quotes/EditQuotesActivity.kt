package com.example.quotes

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import android.view.Menu.NONE
import android.view.MenuItem
import com.example.quotes.models.Quote
import com.example.quotes.mvp.BaseActivity
import com.example.quotes.presenters.EditQuotesPresenter
import com.example.quotes.views.EditQuotesView
import kotlinx.android.synthetic.main.edit_quotes_layout.*


class EditQuotesActivity : BaseActivity<EditQuotesView, EditQuotesPresenter>(), EditQuotesView {

    override fun instantiatePresenter() {
        mPresenter = EditQuotesPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_quotes_layout)
        val quote = intent.getParcelableExtra<Quote>(QuotesDetailActivity.EDIT_QUOTE_EXTRA)
        mPresenter.initActivty(quote)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menu!!.add(NONE, 1, NONE, "Save Quote")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        when (item!!.itemId) {
            1 -> {
                testSaveImage()
            }
        }
        return true
    }

    override fun setUp(quote: Quote) {
        editable_quote_text.isDrawingCacheEnabled = true
        editable_quote_text.text = quote.quote
        editable_quote_author.isDrawingCacheEnabled = true
        editable_quote_author.text = quote.author
    }

    private fun testSaveImage() {
        val bgBmp = (quote_background_image.drawable as BitmapDrawable).bitmap
        val quoteBmp = Bitmap.createBitmap(editable_quote_text.drawingCache)
        val combinedBmp = combineBitmaps(bgBmp, quoteBmp)

        quote_background_image.setImageBitmap(combinedBmp)

        //saving
        //val path = Environment.getExternalStorageDirectory().toString()
        //val file = File(path, "testMergedImage.png")
        //val out = FileOutputStream(file)
        //combinedBmp.compress(Bitmap.CompressFormat.PNG, 100, out)
        //out.close()

    }

    private fun combineBitmaps(background: Bitmap, foreground: Bitmap): Bitmap {
        val cs = Bitmap.createBitmap(background.width, background.height, Bitmap.Config.ARGB_8888)
        val comboImage = Canvas(cs)
        comboImage.drawBitmap(background, 0f, 0f, null)
        comboImage.drawBitmap(foreground, 0f, 0f, null)
        return cs
    }
}