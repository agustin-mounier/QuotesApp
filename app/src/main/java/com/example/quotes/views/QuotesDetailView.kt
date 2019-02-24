package com.example.quotes.views

import com.example.quotes.models.Quote
import com.example.quotes.mvp.BaseViewInterface

interface QuotesDetailView: BaseViewInterface {

    fun setUp(quote: Quote)
}