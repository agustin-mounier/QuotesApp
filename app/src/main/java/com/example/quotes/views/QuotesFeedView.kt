package com.example.quotes.views

import com.example.quotes.models.Quote
import com.example.quotes.mvp.BaseViewInterface

interface QuotesFeedView: BaseViewInterface {

    fun showQuotes(quotes: List<Quote>)

    fun showError(errorMessage: String)

    fun resetQuotesFeed()

    fun setToolbarTitle(title: String)
}