package com.example.quotes.views

import com.example.quotes.models.Quote
import com.example.quotes.mvp.BaseViewInterface

interface EditQuotesView: BaseViewInterface {

    fun setUp(quote: Quote)

    fun setUpFontOptions(fonts: Array<String>)

    fun setUpBackgroundOptions(backgrounds: Array<String>)

    fun setUpTextOptions(textStyles: Map<String, Int>, textAlignments: Map<String, Int>)

    fun setUpColorOptions(colors: Array<String>)

    fun setUpSizeOptions()

    fun removeCurrentOptions()
}