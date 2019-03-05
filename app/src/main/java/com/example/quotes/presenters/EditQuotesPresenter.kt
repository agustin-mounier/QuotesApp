package com.example.quotes.presenters

import android.graphics.Typeface
import android.view.Gravity
import com.example.quotes.models.Quote
import com.example.quotes.mvp.BasePresenter
import com.example.quotes.views.EditQuotesView

class EditQuotesPresenter : BasePresenter<EditQuotesView>() {

    private val fonts = arrayOf(
        "sans-serif",
        "sans-serif-light",
        "sans-serif-condensed",
        "sans-serif-black",
        "sans-serif-thin",
        "sans-serif-medium"
    )

    private val backgrounds = arrayOf(
        "quote_background_1",
        "quote_background_2",
        "quote_background_3",
        "quote_background_4",
        "quote_background_5",
        "quote_background_6",
        "quote_background_7"
    )

    private val textStyles = mapOf(
        Pair("Normal", Typeface.NORMAL),
        Pair("Bold", Typeface.BOLD),
        Pair("Italic", Typeface.ITALIC),
        Pair("Bold Italic", Typeface.BOLD_ITALIC)
    )
    private val textAlignments = mapOf(
        Pair("Align Center", Gravity.CENTER_HORIZONTAL),
        Pair("Align Left", Gravity.LEFT),
        Pair("Align Right", Gravity.RIGHT)
    )

    private val colors = arrayOf(
        "#ffffff",
        "#ff0000",
        "#ff4000",
        "#ff8000",
        "#ffbf00",
        "#ffff00",
        "#bfff00",
        "#80ff00",
        "#40ff00",
        "#00ff00",
        "#00ff40",
        "#00ff80",
        "#00ffbf",
        "#00ffff",
        "#00bfff",
        "#0080ff",
        "#0040ff",
        "#0000ff",
        "#4000ff",
        "#8000ff",
        "#bf00ff",
        "#ff00ff",
        "#ff00bf",
        "#ff0080",
        "#ff0040",
        "#000000"
    )

    fun initActivty(quote: Quote) {
        mView!!.setUp(quote)
    }

    fun showFonts() {
        mView!!.removeCurrentOptions()
        mView!!.setUpFontOptions(fonts)
    }

    fun showBackgrounds() {
        mView!!.removeCurrentOptions()
        mView!!.setUpBackgroundOptions(backgrounds)
    }

    fun showTextOptions() {
        mView!!.removeCurrentOptions()
        mView!!.setUpTextOptions(textStyles, textAlignments)
    }

    fun showColorOptions() {
        mView!!.removeCurrentOptions()
        mView!!.setUpColorOptions(colors)
    }

    fun showSizeOptions() {
        mView!!.removeCurrentOptions()
        mView!!.setUpSizeOptions()
    }
}