package com.example.quotes.views

import com.example.quotes.models.Quote
import com.example.quotes.mvp.BaseViewInterface

interface EditQuotesView: BaseViewInterface {

    fun setUp(quote: Quote)
}